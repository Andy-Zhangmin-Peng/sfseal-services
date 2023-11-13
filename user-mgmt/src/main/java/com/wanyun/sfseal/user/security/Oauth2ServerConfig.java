/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import com.wanyun.sfseal.commonutils.common.config.JwtKeyExpiryHandlerProperties;
import com.wanyun.sfseal.user.security.password.WanYunPasswordEncoder;
import com.wanyun.sfseal.user.security.provider.wechat.WechatIdAuthenticationService;
import com.wanyun.sfseal.user.security.provider.wechat.WechatIdTokenGranter;
import com.wanyun.sfseal.commonutils.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is to configure the user microservice as an OAuth2 authorization server.
 * Basically all configurations for this microservice to be an auth server are
 * done here. For more information please refer to Sprint OAuth2 APIs.
 *
 * @author WanYun
 */
@Configuration
@EnableAuthorizationServer
@ComponentScan(basePackageClasses = {
        Oauth2ServerConfigProperties.class,
        JwtKeyExpiryHandlerProperties.class})
@Profile("JWTAuthCore")
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    public final JwtKeyExpiryHandlerProperties jwtKeyExpiryHandlerProperties;
    protected String salt = "WanYun";
    private final ClientDetailsService clientDetailsService;
    private final AuthenticationManager authenticationManager;
    private final Oauth2ServerConfigProperties oAuth2ServerConfigProperties;
    private final WechatIdAuthenticationService wechatIdAuthenticationService;

    public Oauth2ServerConfig(JwtKeyExpiryHandlerProperties jwtKeyExpiryHandlerProperties, ClientDetailsService clientDetailsService, AuthenticationManager authenticationManager, Oauth2ServerConfigProperties oAuth2ServerConfigProperties, WechatIdAuthenticationService wechatIdAuthenticationService) {
        this.jwtKeyExpiryHandlerProperties = jwtKeyExpiryHandlerProperties;
        this.clientDetailsService = clientDetailsService;
        this.authenticationManager = authenticationManager;
        this.oAuth2ServerConfigProperties = oAuth2ServerConfigProperties;
        this.wechatIdAuthenticationService = wechatIdAuthenticationService;
    }

    @Bean
    public KeySupplier keySupplier() {
        return new KeySupplierImpl(new KeystoreKeySupplierHelper(oAuth2ServerConfigProperties), null);
    }

    /**
     * To configure the client name, scope and token validation duration here. The
     * grant type "password" means a username/password pair has to be supplied.
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
         * Even though this says "inMemory," the JWT token usage is still stateless.
         * The same JWT token can be verified on multiple instances
         * (assuming the same key pair is used for each instance).
         */
        clients.inMemory().withClient(oAuth2ServerConfigProperties.getClientId())
                .authorizedGrantTypes(oAuth2ServerConfigProperties.getAuthorizedGrantTypes()
                        .toArray(new String[oAuth2ServerConfigProperties.getAuthorizedGrantTypes().size()]))
                .scopes(oAuth2ServerConfigProperties.getScopes())
                .accessTokenValiditySeconds(oAuth2ServerConfigProperties.getAccessTokenValidity());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(userInformationTokenEnhancer(), accessTokenConverter()));

        endpoints.authenticationManager(this.authenticationManager)
                .userApprovalHandler(userApprovalHandler(tokenStore()))
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .tokenGranter(tokenGranter(endpoints));
    }

    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        granters.add(new WechatIdTokenGranter(this.wechatIdAuthenticationService,
                endpoints.getTokenServices(),
                endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new WanYunPasswordEncoder(salt);
    }

    /**
     * It is very important to have this configuration here. It must allow form
     * authentication so a submission from UI can be handled here.
     * This exposes the public key to consumers.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        assert oauthServer != null;
        oauthServer.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .passwordEncoder(passwordEncoder());
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

    @Bean
    public TokenEnhancer userInformationTokenEnhancer() {
        return new UserInformationTokenEnhancer();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(userInformationTokenEnhancer(), accessTokenConverter()));
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(false);
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setAccessTokenValiditySeconds(oAuth2ServerConfigProperties.getAccessTokenValidity());

        return defaultTokenServices;
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }
}
