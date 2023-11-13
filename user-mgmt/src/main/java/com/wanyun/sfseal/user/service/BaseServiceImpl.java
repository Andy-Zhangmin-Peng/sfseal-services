/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.model.AuthorityModel;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This is the base class of all ServiceImpl within user domain.
 * Any common methods of this domain should be in this class.
 *
 * @author WanYun
 */
public class BaseServiceImpl {

    /**
     * Get the current login user info
     *
     * @return the authority model
     */
    protected AuthorityModel getCurrentLoginInfo() {
        AuthorityModel authority = new AuthorityModel();
        String[] authStr = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority().split(CommonConstants.COLON);
        authority.setAccountId(safeGetLong(authStr[2]));
        authority.setAccountName(authStr[3]);
        authority.setCompanyId(safeGetLong(authStr[4]));
        authority.setUserId(safeGetLong(authStr[5]));
        return authority;
    }

    /**
     * 安全的转换String 成Long
     * 支持包含Null，以及Null String
     *
     * @param stringifyLong
     * @return
     */
    private Long safeGetLong(String stringifyLong) {
        if (stringifyLong == null) {
            return null;
        } else if ("null".equalsIgnoreCase(stringifyLong)) {
            return null;
        } else {
            return NumberUtils.createLong(stringifyLong);
        }
    }
}
