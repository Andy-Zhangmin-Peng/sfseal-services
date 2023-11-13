/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A properties class mapping to application.yml. Each variable maps to a key of properties file.
 *
 * @author WanYun
 */
@ConfigurationProperties(prefix = "user")
public class UserConfigProperties {
    public UserConfigProperties() {
    }
}
