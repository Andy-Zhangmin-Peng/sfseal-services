/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.user.AccountModel;
import com.wanyun.sfseal.user.service.AccountModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

/**
 * This class is used for convert hub next user to Spring user
 *
 * @author WanYun
 */
@Component("WanYunUserDetailsService")
public class WanYunUserDetailsServiceImpl implements WanYunUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WanYunUserDetailsServiceImpl.class);

    private AccountModelService accountService;

    public WanYunUserDetailsServiceImpl(AccountModelService accountService) {
        this.accountService = accountService;
    }

    /**
     * We will use this method to get user account info from database and convert it
     * to spring security user Parameters: username the username presented to the
     * DaoAuthenticationProvider password the password that should be presented to
     * the DaoAuthenticationProvider enabled set to true if the user is enabled
     * accountNonExpired set to true if the account has not expired
     * credentialsNonExpired set to true if the credentials have not expired
     * accountNonLocked set to true if the account is not locked authorities the
     * authorities that should be granted to the caller if they presented the
     * correct username and password and the user is enabled. Not null.
     *
     * @param username the user name
     * @return user
     */
    @Override
    public UserDetails loadUserByUsername(String username, UsernamePasswordAuthenticationToken authentication) {
        UserDetails user = null;
        JSONObject object = JSON.parseObject(JSON.toJSONString(authentication.getDetails()));
        String usage = object.getString("usage");
        try {
            LOGGER.info("Current User name:" + username);
            AccountModel account = accountService.getFullAccountByUserInfo(URLDecoder.decode(username, CommonConstants.ENCODING));
            Collection<? extends GrantedAuthority> authorityList = UserDetailUtils.assembleAuthorities(account, usage);
            user = new User(account.getUsername(), account.getPassword(), authorityList);
        } catch (WanYunBusinessException | UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }
}
