/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.PermissionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wanyun
 */
public interface PermissionModelMapper {
    int deleteByPrimaryKey(Long permissionId);

    int insert(PermissionModel record);

    int insertSelective(PermissionModel record);

    PermissionModel selectByPrimaryKey(Long permissionId);

    int updateByPrimaryKeySelective(PermissionModel record);

    int updateByPrimaryKey(PermissionModel record);

    List<PermissionModel> selectPermissionsByRoleId(@Param("roleId") Long roleId);
}