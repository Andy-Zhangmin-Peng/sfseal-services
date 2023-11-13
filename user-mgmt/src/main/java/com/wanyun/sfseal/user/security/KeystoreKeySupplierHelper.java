/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import com.wanyun.sfseal.commonutils.security.LazyJwtAccessTokenConverter;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.security.KeyPair;
import java.util.function.Supplier;

/**
 * This methods take proxy key supplier helper.
 *
 * @author WanYun
 */
public class KeystoreKeySupplierHelper implements Supplier<KeyPair> {
    private Oauth2ServerConfigProperties oAuth2ServerConfigProperties;

    public KeystoreKeySupplierHelper(Oauth2ServerConfigProperties oAuth2ServerConfigProperties) {
        this.oAuth2ServerConfigProperties = oAuth2ServerConfigProperties;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public KeyPair get() {
        KeyPair pair;
        Resource keystore;
        try {
            keystore = new UrlResource(oAuth2ServerConfigProperties.getKeystorePath());
        } catch (MalformedURLException ex) {
            throw new BeanInstantiationException(LazyJwtAccessTokenConverter.class, ex.getMessage(), ex);
        }
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keystore,
                oAuth2ServerConfigProperties.getKeystorePassword().toCharArray());
        String keyPairAlias = oAuth2ServerConfigProperties.getKeyPairAlias();
        String keyPairAliasPassword = oAuth2ServerConfigProperties.getKeyPairPassword();
        // If a different password is specified for the key pair, use it.
        if (!StringUtils.isEmpty(keyPairAliasPassword)) {
            pair = keyStoreKeyFactory.getKeyPair(keyPairAlias,
                    keyPairAliasPassword.toCharArray());
        } else {
            pair = keyStoreKeyFactory.getKeyPair(keyPairAlias);
        }

        return pair;
    }
}
