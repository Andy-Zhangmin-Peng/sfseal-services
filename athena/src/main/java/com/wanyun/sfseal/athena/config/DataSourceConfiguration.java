/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.athena.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * This is a class to Configure the data source
 *
 * @author WanYun
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * Init HikariDataSource based on this Docker container's environment variables.
     *
     * @return a DataSource instance
     */
    @Bean
    @ConfigurationProperties(prefix = "sfseal.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier(value = "transactionManager")
    public PlatformTransactionManager serviceContainerTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
