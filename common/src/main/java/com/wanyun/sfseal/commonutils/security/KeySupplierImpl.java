/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import java.security.KeyPair;
import java.util.function.Supplier;

/**
 * This is the implementation of KeySupplier for key supplier related business logic.
 *
 * @author WanYun
 */
public class KeySupplierImpl implements KeySupplier {
    private Supplier<KeyPair> keyPairSupplier;
    private Supplier<String> verifierSupplier;

    public KeySupplierImpl(Supplier<KeyPair> keyPairSupplier, Supplier<String> verifierSupplier) {
        if (keyPairSupplier != null) {
            this.keyPairSupplier = keyPairSupplier;
        }
        if (verifierSupplier != null) {
            this.verifierSupplier = verifierSupplier;
        }
    }

    @Override
    public KeyPair getNewKeyPair() {
        if (this.keyPairSupplier != null) {
            return this.keyPairSupplier.get();
        }
        return null;

    }

    @Override
    public String getNewVerifierKey() {
        if (this.verifierSupplier != null) {
            return this.verifierSupplier.get();
        }
        return null;
    }
}
