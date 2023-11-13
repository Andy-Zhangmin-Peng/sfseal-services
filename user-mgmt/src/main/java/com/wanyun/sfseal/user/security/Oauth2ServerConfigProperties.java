/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A properties class mapping to application.properties. Each variable maps to a key of properties file.
 * This class specifically contains the properties for oauth2.
 *
 * @author WanYun
 */
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "oauth2")
public class Oauth2ServerConfigProperties {

    private String clientId;
    private List<String> authorizedGrantTypes;
    private String scopes;

    private String keystorePath;
    private String keystorePassword;
    private String keyPairAlias;
    private String keyPairPassword;
    private int accessTokenValidity;

    /**
     * @return the clientID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientID to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the authorizedGrantTypes
     */
    public List<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * @param authorizedGrantTypes the authorizedGrantTypes to set
     */
    public void setAuthorizedGrantTypes(List<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @return the scopes
     */
    public String getScopes() {
        return scopes;
    }

    /**
     * @param scopes the scopes to set
     */
    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    /**
     * @return the keystorePath
     */
    public String getKeystorePath() {
        return keystorePath;
    }

    /**
     * @param keystorePath the keystorePath to set
     */
    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    /**
     * @return the keystorePassword
     */
    public String getKeystorePassword() {
        return keystorePassword;
    }

    /**
     * @param keystorePassword the keystorePassword to set
     */
    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    /**
     * @return the keyPairAlias
     */
    public String getKeyPairAlias() {
        return keyPairAlias;
    }

    /**
     * @param keyPairAlias the keyPairAlias to set
     */
    public void setKeyPairAlias(String keyPairAlias) {
        this.keyPairAlias = keyPairAlias;
    }

    /**
     * @return the keyPairPassword
     */
    public String getKeyPairPassword() {
        return keyPairPassword;
    }

    /**
     * @param keyPairPassword the keyPairPassword to set
     */
    public void setKeyPairPassword(String keyPairPassword) {
        this.keyPairPassword = keyPairPassword;
    }

    /**
     * @return the accessTokenValidity
     */
    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * @param accessTokenValidity the dbPassword to set
     */
    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

}
