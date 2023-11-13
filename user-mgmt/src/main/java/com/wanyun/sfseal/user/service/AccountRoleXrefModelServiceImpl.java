/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.mapper.user.AccountRoleXrefModelMapper;
import com.wanyun.sfseal.db.model.user.AccountRoleXrefModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * This service is the implementation of AccountModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class AccountRoleXrefModelServiceImpl implements AccountRoleXrefModelService {

    @Resource
    private AccountRoleXrefModelMapper accountRoleXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return accountRoleXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(AccountRoleXrefModel record) {
        return accountRoleXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(AccountRoleXrefModel record) {
        return accountRoleXrefModelMapper.insertSelective(record);
    }

    @Override
    public AccountRoleXrefModel selectByPrimaryKey(Long xrefId) {
        return accountRoleXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(AccountRoleXrefModel record) {
        return accountRoleXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AccountRoleXrefModel record) {
        return accountRoleXrefModelMapper.updateByPrimaryKey(record);
    }

}
