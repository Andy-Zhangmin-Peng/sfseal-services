/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import java.security.KeyPair;

/**
 * The interface of KeySupplierImpl
 * A impl class is the actual implementation of the service.
 *
 * @author WanYun
 */
public interface KeySupplier {
    /**
     * Get a new key pair.
     *
     * @return
     */
    KeyPair getNewKeyPair();

    /**
     * Get a new verifier key
     *
     * @return
     */
    String getNewVerifierKey();
}
