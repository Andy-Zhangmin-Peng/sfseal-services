/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.mapper.user.RolePermissionXrefModelMapper;
import com.wanyun.sfseal.db.model.user.RolePermissionXrefModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * This service is the implementation of RolePermissionXrefModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class RolePermissionXrefModelServiceImpl implements RolePermissionXrefModelService {

    @Resource
    private RolePermissionXrefModelMapper rolePermissionXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return rolePermissionXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(RolePermissionXrefModel record) {
        return rolePermissionXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(RolePermissionXrefModel record) {
        return rolePermissionXrefModelMapper.insertSelective(record);
    }

    @Override
    public RolePermissionXrefModel selectByPrimaryKey(Long xrefId) {
        return rolePermissionXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(RolePermissionXrefModel record) {
        return rolePermissionXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RolePermissionXrefModel record) {
        return rolePermissionXrefModelMapper.updateByPrimaryKey(record);
    }

}
