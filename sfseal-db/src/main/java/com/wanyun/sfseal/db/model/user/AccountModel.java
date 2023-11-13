/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.db.model.company.CompanyModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * @author wanyun
 */
@Data
public class AccountModel {
    /**
     * 管理员账号ID
     */
    private Long accountId;

    /**
     * 登录名
     */
    @NotNull(message = "")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "")
    private String password;

    /**
     * 管理员手机号
     */
    private String phoneNumber;

    /**
     * 管理员邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Timestamp createDate;

    /**
     * 上次更新时间
     */
    private Timestamp lastUpdateDate;

    /**
     * 上次更新人
     */
    private String lastUpdateUser;

    private UserModel user;

    private StaffModel staff;

    private List<PermissionModel> permissions;

    private List<RoleModel> roles;

    private CompanyModel company;

    public List<PermissionModel> getPermissions() {
        if (CommonUtil.isListEmpty(this.permissions) && !CommonUtil.isListEmpty(roles)) {
            this.permissions = new ArrayList<>(this.loadPermission(roles, null));
        }
        return permissions;
    }

    public List<PermissionModel> getPermissions(String usage) {
        if (CommonUtil.isListEmpty(this.permissions) && !CommonUtil.isListEmpty(roles)) {
            this.permissions = new ArrayList<>(this.loadPermission(roles, usage));
        }
        return permissions;
    }

    private List<PermissionModel> loadPermission(List<RoleModel> roles, String usage) {
        List<PermissionModel> permissions = new ArrayList<>();
        if (!CommonUtil.isListEmpty(roles)) {
            roles.forEach(role -> {
                if (usage == null || CommonUtil.compareObjects(role.getUsage(), usage)) {
                    if (!CommonUtil.isListEmpty(role.getPermissions())) {
                        permissions.addAll(role.getPermissions());
                    }
                }
            });
        }
        return permissions;
    }
}