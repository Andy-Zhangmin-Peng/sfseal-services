/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.model.user.AccountRoleXrefModel;

/**
 * This service is the interface of AccountRoleXrefModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface AccountRoleXrefModelService {

    int deleteByPrimaryKey(Long xrefId);

    int insert(AccountRoleXrefModel record);

    int insertSelective(AccountRoleXrefModel record);

    AccountRoleXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(AccountRoleXrefModel record);

    int updateByPrimaryKey(AccountRoleXrefModel record);

}
