/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

/**
 * The interface of KeySupplierImpl
 * A impl class is the actual implementation of the service.
 *
 * @author WanYun
 */
public interface ExpiryHandler {

    /**
     * This method would acquire the current timestamp and compare it against the field
     * (relative to the timeoutMillis – the same check logic as evaluateKeys below).
     *
     * @return
     */
    Boolean isExpired();

    /**
     * This method would set the timestamp to the current timestamp
     */
    void reset();
}
