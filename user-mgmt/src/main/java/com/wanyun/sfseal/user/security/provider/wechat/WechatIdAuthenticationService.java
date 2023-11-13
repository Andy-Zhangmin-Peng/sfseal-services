package com.wanyun.sfseal.user.security.provider.wechat;

import org.springframework.security.core.Authentication;

/**
 * @author WanYun
 */
public interface WechatIdAuthenticationService {

    /**
     * Authenticate with given authentication data
     *
     * @param authentication
     * @return
     */
    WechatIdAuthenticationToken authenticate(Authentication authentication);
}
