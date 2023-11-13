/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import com.wanyun.sfseal.user.security.password.WanYunPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * General Spring Security configuration is set here. It must define
 * authentication manager and UserDetailsService implementation here to do
 * actual validation against username/password.
 *
 * @author WanYun
 */
@Configuration
@EnableWebSecurity
@Order(Integer.MAX_VALUE)
public class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter {
    protected String salt = "WanYun";

    @Autowired
    private WanYunAuthenticationProvider wanYunAuthenticationProvider;

    /**
     * Nothing is permitted to be accessed directly except "/oauth/token" URI for
     * getting a token.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().anonymous().disable()
                .authorizeRequests().antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(wanYunAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new WanYunPasswordEncoder(salt);
    }
}
