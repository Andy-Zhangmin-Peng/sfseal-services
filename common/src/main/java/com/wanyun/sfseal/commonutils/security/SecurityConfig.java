/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import com.wanyun.sfseal.commonutils.common.config.JwtKeyExpiryHandlerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Customize security config
 *
 * @author WanYun
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class SecurityConfig {
    @Configuration
    @EnableResourceServer
    @Profile("JWTAuth")
    @ComponentScan(basePackageClasses = {JwtKeyExpiryHandlerProperties.class})
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Autowired
        public JwtKeyExpiryHandlerProperties jwtKeyExpiryHandlerProperties;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(new String[]{
                            "/**/noauth/**",
                            "/noauth/**",
                            "/",
                            "/index.html",
                            "/**/index.html",
                            "/actuator/**",
                            "/**/swagger/**",
                            "/external/**"})
                    .permitAll()
                    .anyRequest().authenticated();
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer config) {
            config.tokenStore(tokenStore());
        }

        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

        @Bean
        KeySupplier keySupplier() {
            return new KeySupplierImpl(null, new ProxyKeySupplierHelper());
        }

        @Bean
        public ExpiryHandler expiryHandler() {
            return new TimestampExpiryHandler(jwtKeyExpiryHandlerProperties.getTimeout());
        }

        @Bean
        @ConditionalOnProperty(value = "oauth2.keystore.path", matchIfMissing = true)
        public LazyJwtAccessTokenConverter accessTokenConverter() {
            return new LazyJwtAccessTokenConverter(keySupplier(), expiryHandler());
        }
    }
}
