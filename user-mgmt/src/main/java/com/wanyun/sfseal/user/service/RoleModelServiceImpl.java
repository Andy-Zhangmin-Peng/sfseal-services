/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.mapper.user.RoleModelMapper;
import com.wanyun.sfseal.db.model.user.RoleModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * This service is the implementation of RoleModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class RoleModelServiceImpl implements RoleModelService {

    @Resource
    private RoleModelMapper roleModelMapper;

    @Override
    public int deleteByPrimaryKey(Long roleId) {
        return roleModelMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(RoleModel record) {
        return roleModelMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleModel record) {
        return roleModelMapper.insertSelective(record);
    }

    @Override
    public RoleModel selectByPrimaryKey(Long roleId) {
        return roleModelMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleModel record) {
        return roleModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleModel record) {
        return roleModelMapper.updateByPrimaryKey(record);
    }

}
