/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.model.user.PermissionModel;

/**
 * This service is the interface of PermissionModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface PermissionModelService {


    int deleteByPrimaryKey(Long permissionId);

    int insert(PermissionModel record);

    int insertSelective(PermissionModel record);

    PermissionModel selectByPrimaryKey(Long permissionId);

    int updateByPrimaryKeySelective(PermissionModel record);

    int updateByPrimaryKey(PermissionModel record);

}
