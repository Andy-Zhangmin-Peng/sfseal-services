/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author wanyun
 */
@Data
public class RoleModel {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色显示名
     */
    private String roleDisplayName;

    /**
     * 角色描述
     */
    private String roleDesc;

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

    private String usage;

    private List<PermissionModel> permissions;
}