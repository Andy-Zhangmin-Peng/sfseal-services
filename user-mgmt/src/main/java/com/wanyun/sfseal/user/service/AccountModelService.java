/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.user.AccountModel;

/**
 * This service is the interface of AccountModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface AccountModelService {

    int deleteByPrimaryKey(Long accountId);

    int insert(AccountModel record);

    int insertSelective(AccountModel record);

    AccountModel selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(AccountModel record);

    int updateByPrimaryKey(AccountModel record);

    AccountModel getFullAccountByUserInfo(String username) throws WanYunBusinessException;

    /**
     * Get full account by account id
     *
     * @return the account
     * @throws WanYunBusinessException
     */
    AccountModel getCurrentAccountInfo() throws WanYunBusinessException;

    /**
     * Get full account by account id
     *
     * @param accountId
     * @return
     * @throws WanYunBusinessException
     */
    AccountModel getFullAccountById(Long accountId) throws WanYunBusinessException;

    /**
     * 在创建账号时，检查必要的值是否已经存在
     *
     * @param valueType
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException;
}
