/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.RoleModel;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author wanyun
 */
public interface RoleModelMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    RoleModel selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);

    List<Role> selectRolesByAccountId(Long accountId);


}