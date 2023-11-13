/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.db.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A properties class mapping to application.yml. Each variable maps to a key of properties file.
 *
 * @author WanYun
 */
@ConfigurationProperties(prefix = "db")
public class DbConfigProperties {
    public DbConfigProperties() {
    }

    private String mapperxml;

    public String getMapperxml() {
        return mapperxml;
    }

    public void setMapperxml(String mapperxml) {
        this.mapperxml = mapperxml;
    }
}
