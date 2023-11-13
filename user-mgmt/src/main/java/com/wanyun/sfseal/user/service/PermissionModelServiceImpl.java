package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.mapper.user.PermissionModelMapper;
import com.wanyun.sfseal.db.model.user.PermissionModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * This service is the implementation of PermissionModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class PermissionModelServiceImpl implements PermissionModelService {

    @Resource
    private PermissionModelMapper permissionModelMapper;

    @Override
    public int deleteByPrimaryKey(Long permissionId) {
        return permissionModelMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public int insert(PermissionModel record) {
        return permissionModelMapper.insert(record);
    }

    @Override
    public int insertSelective(PermissionModel record) {
        return permissionModelMapper.insertSelective(record);
    }

    @Override
    public PermissionModel selectByPrimaryKey(Long permissionId) {
        return permissionModelMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionModel record) {
        return permissionModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PermissionModel record) {
        return permissionModelMapper.updateByPrimaryKey(record);
    }

}
