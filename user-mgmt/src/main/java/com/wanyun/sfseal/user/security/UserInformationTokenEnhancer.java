/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * This Enhancer is used to add additional User related information into JWT tokens provided by UserAccessService.
 * When applicable, the enhancer will add username as additional information in
 * the token.
 *
 * @author WanYun
 */
public class UserInformationTokenEnhancer implements TokenEnhancer {
    /**
     * Customizes the JWT token to be issued by UserAccessService to have additional information about the user in the
     * token's additional information field.
     *
     * @param accessToken    The OAuth2AcccessToken to be customized with extra user information
     * @param authentication
     * @return The updated OAuth2AccessToken with extra user information.
     * If authentication's getPrincipal() method does not return an instance of UserDetails, then the
     * originally provided token is returned
     * If authentication's getPrincipal() method does return an instance of UserDetails, then the User's
     * username plus any of the following fields are added to the token if they are not null:
     * fullUsername, userID, and email
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object authPrincipal = authentication.getPrincipal();

        Map<String, Object> additionalInfo = new HashMap<>(16);
        if (authPrincipal instanceof UserDetails) {
            UserDetails user = (UserDetails) authPrincipal;

            //JWT Auth Token entry name for the User's "username" from the database
            additionalInfo.put("username", user.getUsername());

            // These claims are required by the flask-extended-jwt library used by the datalake. Jwt tokens will be
            // rejected by the datalake unless these fields are present.
            additionalInfo.put("type", "access");
            additionalInfo.put("fresh", "fresh");
        }


        if (!additionalInfo.isEmpty()) {
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                    additionalInfo);
        }

        return accessToken;
    }
}
