/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.db.common.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * A properties class mapping to application.yml. Each variable maps to a key of properties file.
 *
 * @author WanYun
 */
@Data
@EqualsAndHashCode
@ToString
@Configuration
@ConfigurationProperties(prefix = "sfseal.user")
public class UserConfigProperties {
    private Long userPictureSizeMax;
    private Long userPictureSizeBaseline;
}
