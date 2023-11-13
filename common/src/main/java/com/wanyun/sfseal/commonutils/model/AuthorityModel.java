/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.model;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author WanYun
 */
@SuppressWarnings("serial")
public class AuthorityModel implements GrantedAuthority {
    private Long permissionId;

    private String permissionName;

    private Long accountId;

    private String accountName;

    private Long companyId;

    private Long userId;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getAuthority() {
        return permissionId + CommonConstants.COLON
                + permissionName + CommonConstants.COLON
                + accountId + CommonConstants.COLON
                + accountName + CommonConstants.COLON
                + companyId + CommonConstants.COLON
                + userId + CommonConstants.COLON;
    }
}
