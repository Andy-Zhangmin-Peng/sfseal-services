/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the implementation of ExpiryHandler for expiry handler related business logic.
 * A serviceImpl class is the implementation of a service interface.
 *
 * @author WanYun
 */
public class TimestampExpiryHandler implements ExpiryHandler {
    /**
     * The timeout/expiry of the keys
     */
    @Getter
    @Setter
    private Long timeoutMillis;

    /**
     * The timestamp representing the last time the keys were updated in this object.
     */
    private volatile Long timestamp = 0L;

    public TimestampExpiryHandler() {
        this.setTimeoutMillis(60000L);
    }

    public TimestampExpiryHandler(Long timeoutMillis) {
        if (timeoutMillis == null) {
            this.setTimeoutMillis(60000L);
        } else {
            this.setTimeoutMillis(timeoutMillis);
        }
    }

    /**
     * @return
     */
    @Override
    public Boolean isExpired() {
        return this.timestamp == 0 || (this.timeoutMillis != null && this.timeoutMillis > 0 && (System.currentTimeMillis() - this.timestamp > this.timeoutMillis));
    }

    /**
     *
     */
    @Override
    public void reset() {
        this.timestamp = System.currentTimeMillis();
    }
}
