/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import com.wanyun.sfseal.commonutils.common.config.JwtKeyExpiryHandlerProperties;
import com.wanyun.sfseal.commonutils.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Here is indicating the user microservice is also a resource server. It's toke
 * store and converter are defined same as the auth server.
 *
 * @author WanYun
 */
@Configuration
@EnableResourceServer
@ComponentScan(basePackageClasses = {
        Oauth2ServerConfigProperties.class,
        JwtKeyExpiryHandlerProperties.class})
@Profile("JWTAuthCore")
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    public JwtKeyExpiryHandlerProperties jwtKeyExpiryHandlerProperties;

    @Autowired
    private Oauth2ServerConfigProperties oAuth2ServerConfigProperties;

    @Bean
    public KeySupplier keySupplier() {
        return new KeySupplierImpl(new KeystoreKeySupplierHelper(oAuth2ServerConfigProperties), null);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("**/noauth/**", "/noauth/**", "/users/status", "/users/answer", "/users/reset", "/users/password-strategy", "/actuator/**", "/**/swagger/**").permitAll()
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
    public ExpiryHandler expiryHandler() {
        return new TimestampExpiryHandler(jwtKeyExpiryHandlerProperties.getTimeout());
    }

    @Bean
    public LazyJwtAccessTokenConverter accessTokenConverter() {
        return new LazyJwtAccessTokenConverter(keySupplier(), expiryHandler());
    }
}
