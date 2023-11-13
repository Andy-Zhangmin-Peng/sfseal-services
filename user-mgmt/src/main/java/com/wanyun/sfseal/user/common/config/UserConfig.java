/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.common.config;

import com.wanyun.sfseal.commonutils.common.contants.UrlConstants;
import com.wanyun.sfseal.commonutils.common.proxy.RestfulWebClient;
import com.wanyun.sfseal.commonutils.security.WanYunMethodSecurityExpressionHandler;
import com.wanyun.sfseal.commonutils.security.WanYunMethodSecurityExpressionRoot;
import com.wanyun.sfseal.commonutils.security.MethodSecurityConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * To initialize basic beans into container
 *
 * @author WanYun
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(UserConfigProperties.class)
@ComponentScan(basePackages = "com.wanyun.sfseal.commonutils.common.config")
@ComponentScan(basePackageClasses = {
        WanYunMethodSecurityExpressionHandler.class,
        WanYunMethodSecurityExpressionRoot.class,
        MethodSecurityConfig.class})
public class UserConfig {

    @Bean("wechatRestfulWebClient")
    public RestfulWebClient wechatRestfulWebClient() {
        return new RestfulWebClient(UrlConstants.WechatUrlConstants.WECHAT_API_BASE_URL, (headers) -> {
        });
    }
}
