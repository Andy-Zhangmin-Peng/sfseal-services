package com.wanyun.sfseal.user.security.provider.wechat;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * QuCamp customized token granter authenticate the user information by given wechat id and code
 * @author WanYun
 */
public class WechatIdTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "wechat_id";

    private final WechatIdAuthenticationService wechatIdAuthenticationService;

    public WechatIdTokenGranter(WechatIdAuthenticationService wechatIdAuthenticationService,
                                AuthorizationServerTokenServices tokenServices,
                                ClientDetailsService clientDetailsService,
                                OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, WechatIdTokenGranter.GRANT_TYPE);
        this.wechatIdAuthenticationService = wechatIdAuthenticationService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = tokenRequest.getRequestParameters();
        String wechatId = parameters.get("wechat_id");
        String authorizationCode = parameters.get("code");
        WechatIdAuthenticationToken userAuth = new WechatIdAuthenticationToken(wechatId, authorizationCode);
        userAuth.setDetails(parameters);
        try {
            userAuth = wechatIdAuthenticationService.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }

        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate wechat id: " + wechatId);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);

    }

}
