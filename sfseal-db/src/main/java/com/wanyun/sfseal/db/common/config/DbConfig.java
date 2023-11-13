/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.db.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * To initialize basic beans into container
 *
 * @author WanYun
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DbConfigProperties.class)
@MapperScan(basePackages = "com.wanyun.sfseal.db.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@ComponentScan(basePackages = "com.wanyun.sfseal.commonutils.common.config")
public class DbConfig {

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource,
                                               DbConfigProperties dbConfigProperties) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(dbConfigProperties.getMapperxml());
        factoryBean.setMapperLocations(resources);
        // Setup type handler location
        factoryBean.setTypeHandlersPackage("com.wanyun.sfseal.db.common.type");
        return factoryBean.getObject();
    }

}
