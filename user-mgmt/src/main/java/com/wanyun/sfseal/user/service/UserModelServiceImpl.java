/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.db.mapper.company.CompanyAccountXrefModelMapper;
import com.wanyun.sfseal.db.mapper.user.AccountModelMapper;
import com.wanyun.sfseal.db.mapper.user.AccountRoleXrefModelMapper;
import com.wanyun.sfseal.db.mapper.user.UserModelMapper;
import com.wanyun.sfseal.db.model.company.CompanyAccountXrefModel;
import com.wanyun.sfseal.db.model.user.*;
import com.wanyun.sfseal.user.security.password.WanYunPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * This service is the implementation of UserAddressModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class UserModelServiceImpl extends BaseServiceImpl implements UserModelService {

    @Resource
    private UserModelMapper userModelMapper;

    @Resource
    private AccountModelMapper accountModelMapper;

    @Resource
    private AccountRoleXrefModelMapper accountRoleXrefModelMapper;

    @Resource
    private CompanyAccountXrefModelMapper companyAccountXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userModelMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserModel record) {
        return userModelMapper.insert(record);
    }

    @Override
    public int insertSelective(UserModel record) {
        return userModelMapper.insertSelective(record);
    }

    @Override
    public UserModel selectByPrimaryKey(Long userId) {
        return userModelMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserModel record) {
        return userModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserModel record) {
        return userModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserModel> getUsersByCondition(UserSearchModel condition) {
        Long companyId = this.getCurrentLoginInfo().getCompanyId();
        condition.setCompanyId(companyId);
        return userModelMapper.getUsersByCondition(condition);
    }

    @Override
    public AccountModel createUserIncludeAccount(AccountModel accountModel) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        accountModel.setCreateDate(currentTimeStamp);
        accountModel.setPassword(getPassword(accountModel.getPassword()));
        accountModel.setLastUpdateDate(currentTimeStamp);
        accountModel.setLastUpdateUser(currentAccountName);
        accountModelMapper.insert(accountModel);

        //向数据库添加Account和社群关系
        CompanyAccountXrefModel companyAccountXrefModel = new CompanyAccountXrefModel();
        companyAccountXrefModel.setAccountId(accountModel.getAccountId());
        companyAccountXrefModel.setCompanyId(companyId);
        companyAccountXrefModel.setCreateDate(currentTimeStamp);
        companyAccountXrefModel.setLastUpdateDate(currentTimeStamp);
        companyAccountXrefModel.setLastUpdateUser(currentAccountName);
        companyAccountXrefModelMapper.insert(companyAccountXrefModel);

        AccountRoleXrefModel accountRoleXrefModel = new AccountRoleXrefModel();
        accountRoleXrefModel.setAccountId(accountModel.getAccountId());
        accountRoleXrefModel.setRoleId(3L);
        accountRoleXrefModel.setCreateDate(currentTimeStamp);
        accountRoleXrefModel.setLastUpdateDate(currentTimeStamp);
        accountRoleXrefModel.setLastUpdateUser(currentAccountName);
        accountRoleXrefModelMapper.upsert(accountRoleXrefModel);

        UserModel userModel = accountModel.getUser();
        userModel.setAccountId(accountModel.getAccountId());
        userModel.setCreateDate(currentTimeStamp);
        userModel.setLastUpdateDate(currentTimeStamp);
        userModel.setLastUpdateUser(currentAccountName);
        userModel.setIsActive(true);
        userModelMapper.insert(userModel);

        return accountModel;
    }

    @Override
    public AccountModel updateUserIncludeAccount(AccountModel accountModel) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();

        AccountModel existingAccount = this.accountModelMapper.selectByPrimaryKey(accountModel.getAccountId());

        if (!CommonUtil.compareObjects(existingAccount.getPassword(), accountModel.getPassword())){
            accountModel.setPassword(getPassword(accountModel.getPassword()));
        }

        //向数据库更新Account信息
        accountModel.setLastUpdateDate(currentTimeStamp);
        accountModel.setLastUpdateUser(currentAccountName);
        accountModelMapper.updateByPrimaryKey(accountModel);

        // 给公司账号添加详细信息
        UserModel userModel = accountModel.getUser();
        userModel.setAccountId(accountModel.getAccountId());
        userModel.setLastUpdateDate(currentTimeStamp);
        userModel.setLastUpdateUser(currentAccountName);
        userModel.setIsActive(true);
        userModelMapper.updateByPrimaryKey(userModel);

        return accountModel;
    }

    @Override
    public Integer inactiveUser(Long accountId) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();

        UserModel userModel = new UserModel();
        userModel.setAccountId(accountId);
        userModel.setIsActive(false);
        userModel.setLastUpdateDate(currentTimeStamp);
        userModel.setLastUpdateUser(currentAccountName);

        return userModelMapper.inactiveUserByAccountId(userModel);
    }

    /**
     * salt password
     *
     * @param password
     * @return
     */
    public String getPassword(String password) {
        WanYunPasswordEncoder passwordEncoder = new WanYunPasswordEncoder("WanYun");
        String encodePassword = passwordEncoder.encodePurePassword(password);
        return encodePassword;
    }
}
