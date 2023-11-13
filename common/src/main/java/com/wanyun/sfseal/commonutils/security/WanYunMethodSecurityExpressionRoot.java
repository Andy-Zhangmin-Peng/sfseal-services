/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.commonutils.model.AuthorityModel;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Customize security expression root, all permission definitions are here
 *
 * @author WanYun
 */
public class WanYunMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Collection<AuthorityModel> authorities;

    private Object filterObject;
    private Object returnObject;
    private Object target;

    WanYunMethodSecurityExpressionRoot(Authentication auth) {
        super(auth);
        authorities = new ArrayList<>();

        for (GrantedAuthority authority : auth.getAuthorities()) {
            String[] authStr = authority.getAuthority().split(CommonConstants.COLON);
            AuthorityModel customizedAuth = new AuthorityModel();
            customizedAuth.setPermissionId(Long.parseLong(authStr[0]));
            customizedAuth.setPermissionName(authStr[1]);
            customizedAuth.setAccountId(Long.parseLong(authStr[2]));
            customizedAuth.setAccountName(authStr[3]);
            customizedAuth.setCompanyId(Long.parseLong(authStr[4]));
            customizedAuth.setUserId(Long.parseLong(authStr[5]));
            authorities.add(customizedAuth);
        }
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    /**
     * Sets the "this" property for use in expressions. Typically this will be the
     * "this" property of the {@code JoinPoint} representing the method invocation
     * which is being protected.
     *
     * @param target the target object on which the method in is being invoked.
     */
    void setThis(Object target) {
        this.target = target;
    }

    /**
     * Authority Validation
     *
     * @param authority the authority
     * @return boolean
     */
    public boolean wanYunAuthority(String authority) {
        for (AuthorityModel auth : authorities) {
            if (CommonConstants.ALL_PERMISSION.equals(auth.getPermissionName()) ||
                    (authority.equals(auth.getPermissionName()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Authority Validation ,check permissionName
     *
     * @param authority the authority
     * @return boolean
     */
    public boolean wanYunAuthority(String authority, Object obj) {
        for (AuthorityModel auth : authorities) {
            if (CommonConstants.ALL_PERMISSION.equals(auth.getPermissionName())) {
                return true;
            } else if (authority.equals(auth.getPermissionName())) {
                if ((authority.equals(CommonConstants.Permission.PERMISSION_VIEW_USER_PERSONAL) ||
                        authority.equals(CommonConstants.Permission.PERMISSION_UPDATE_USER_PERSONAL) ||
                        authority.equals(CommonConstants.Permission.PERMISSION_UPDATE_STAFF_PERSONAL))) {
                    return CommonUtil.compareObjects(auth.getAccountId(), obj);
                }
            }
        }
        return false;
    }
}
