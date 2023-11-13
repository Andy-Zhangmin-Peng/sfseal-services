/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * A properties class mapping to application.yml. Each variable maps to a key of properties file.
 *
 * @author WanYun
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "jwt.key.expiry")
public class JwtKeyExpiryHandlerProperties {
    /**
     * The timeout/expiry of the keys
     */
    private Long timeout;

    /**
     * @return the timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout to set
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
