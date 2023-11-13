/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class provides a lazy evaluation of the signing key pair and/or the verifier key.
 * The keys are evaluated upon the first call to encode or decode.
 * They are also re-evaluated in accordance with the specified timeout.
 *
 * @author WanYun
 */
public class LazyJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KeySupplier keySupplier;

    private ExpiryHandler expiryHandler;

    private ReadWriteLock lock;

    /**
     * Creates a new LazyJwtAccessTokenConverter. The given functions supply the
     * signing key pair and/or the verifier key. These are re-evaluated in
     * accordance with the timeout.
     * If the timeout is null or <= 0, the keys will not be re-evaluated once set.
     * If either supplier is null, the corresponding key will not be re-evaluated
     * after the initial, lazy evaulation (the first call to encode or decode).
     *
     * @param keySupplier
     * @param expiryHandler
     */
    public LazyJwtAccessTokenConverter(KeySupplier keySupplier, ExpiryHandler expiryHandler) {
        this.keySupplier = keySupplier;
        this.expiryHandler = expiryHandler;
        this.lock = new ReentrantReadWriteLock();
        // Initial public key at first time
        evaluateKeys();
    }

    /**
     * Updates the key pair and then the verifier key in a thread-safe manner. If
     * both are non-null, the verifier will override the verifier key of the key
     * pair.
     *
     * @param pair     the signing key pair
     * @param verifier the verifier key
     */
    private void updateKeysHelper(KeyPair pair, String verifier) {
        /*
         * This method is synchronized to support the scenario where either encode or
         * decode are called from multiple threads.
         */
        lock.writeLock().lock();
        try {
            if (pair != null) {
                super.setKeyPair(pair);
            }
            if (verifier != null) {
                super.setVerifierKey(verifier);
                try {
                    // reset the verifier
                    super.setVerifier(null);
                    // create an RSA verifier from verifierKey
                    super.afterPropertiesSet();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * If the current timestamp is at least timeoutMillis greater than the last
     * update timestamp (or if this is the first call), trigger a re-evaluation of
     * the keys.
     */
    @Async
    protected void evaluateKeys() {
        if (this.expiryHandler.isExpired()) {
            updateKeysHelper(this.keySupplier.getNewKeyPair(), this.keySupplier.getNewVerifierKey());
            this.expiryHandler.reset();
        }
    }

    /**
     * This method is called to encode a token using the signing key.
     *
     * @param accessToken    the token to encode
     * @param authentication authentication details
     * @return the encoded token
     */
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        evaluateKeys();
        String encoded;
        lock.readLock().lock();
        try {
            encoded = super.encode(accessToken, authentication);
        } finally {
            lock.readLock().unlock();
        }

        return encoded;
    }

    /**
     * This method is called to decode a token using the verifier key
     *
     * @param token the token to decode
     * @return the decoded token
     */
    @Override
    protected Map<String, Object> decode(String token) {
        evaluateKeys();
        Map<String, Object> decoded;
        lock.readLock().lock();
        try {
            decoded = super.decode(token);
        } finally {
            lock.readLock().unlock();
        }

        return decoded;
    }
}
