/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanyun
 */
@Data
public class RolePermissionXrefModel {
    /**
     * 关系ID
     */
    private Long xrefId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

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
}