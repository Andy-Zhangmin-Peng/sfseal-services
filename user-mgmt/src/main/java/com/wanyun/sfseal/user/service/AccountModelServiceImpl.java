/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.AuthorityModel;
import com.wanyun.sfseal.db.common.constant.DbErrorMessage;
import com.wanyun.sfseal.db.mapper.user.AccountModelMapper;
import com.wanyun.sfseal.db.model.user.AccountModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * This service is the implementation of AccountModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class AccountModelServiceImpl extends BaseServiceImpl implements AccountModelService {

    @Resource
    private AccountModelMapper accountModelMapper;

    @Override
    public int deleteByPrimaryKey(Long accountId) {
        return accountModelMapper.deleteByPrimaryKey(accountId);
    }

    @Override
    public int insert(AccountModel record) {
        return accountModelMapper.insert(record);
    }

    @Override
    public int insertSelective(AccountModel record) {
        return accountModelMapper.insertSelective(record);
    }

    @Override
    public AccountModel selectByPrimaryKey(Long accountId) {
        return accountModelMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public int updateByPrimaryKeySelective(AccountModel record) {
        return accountModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AccountModel record) {
        return accountModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public AccountModel getFullAccountByUserInfo(String username) throws WanYunBusinessException {
        return accountModelMapper.getFullAccountByUserInfo(username);
    }

    /**
     * Get full account by account id
     *
     * @return the account
     * @throws WanYunBusinessException
     */
    @Override
    public AccountModel getCurrentAccountInfo() throws WanYunBusinessException {

        AuthorityModel authority = this.getCurrentLoginInfo();
        this.getFullAccountById(authority.getAccountId());

        return this.getFullAccountById(authority.getAccountId());
    }

    /**
     * Get full account by account id
     *
     * @param accountId
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public AccountModel getFullAccountById(Long accountId) throws WanYunBusinessException {
        if (accountId == null) {
            throw new WanYunBusinessException(DbErrorMessage.User.NO_ACCOUNT_ID_ERROR);
        }
        return accountModelMapper.getFullAccountById(accountId);
    }

    /**
     * 在创建账号时，检查必要的值是否已经存在
     *
     * @param valueType
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException {
        int count = accountModelMapper.checkValueExisting(valueType, value);
        return count > 0 ? true : false;
    }

}
