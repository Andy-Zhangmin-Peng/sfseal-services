/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.model.user.RolePermissionXrefModel;

/**
 * This service is the interface of RolePermissionXrefModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface RolePermissionXrefModelService {


    int deleteByPrimaryKey(Long xrefId);

    int insert(RolePermissionXrefModel record);

    int insertSelective(RolePermissionXrefModel record);

    RolePermissionXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(RolePermissionXrefModel record);

    int updateByPrimaryKey(RolePermissionXrefModel record);

}
