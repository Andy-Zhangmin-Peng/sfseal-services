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
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "wechat")
public class WechatProperties {
    private String appId;
    private String appSecret;
    private String newActivityTemplateId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getNewActivityTemplateId() {
        return this.newActivityTemplateId;
    }

    public void setNewActivityTemplateId(String newActivityTemplateId) {
        this.newActivityTemplateId = newActivityTemplateId;
    }
}
