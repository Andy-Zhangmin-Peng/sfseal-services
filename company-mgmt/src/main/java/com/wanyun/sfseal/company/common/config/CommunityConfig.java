/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.company.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * To initialize basic beans into container
 *
 * @author WanYun
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(CommunityConfigProperties.class)
public class CommunityConfig {
}
