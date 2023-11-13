/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.RolePermissionXrefModel;

/**
 * @author wanyun
 */
public interface RolePermissionXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(RolePermissionXrefModel record);

    int insertSelective(RolePermissionXrefModel record);

    RolePermissionXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(RolePermissionXrefModel record);

    int updateByPrimaryKey(RolePermissionXrefModel record);
}