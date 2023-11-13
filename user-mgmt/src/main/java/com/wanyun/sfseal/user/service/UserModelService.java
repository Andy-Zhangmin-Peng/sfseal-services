/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.db.model.user.AccountModel;
import com.wanyun.sfseal.db.model.user.UserModel;
import com.wanyun.sfseal.db.model.user.UserSearchModel;

import java.util.List;

/**
 * This service is the interface of UserModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface UserModelService {

    int deleteByPrimaryKey(Long userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    List<UserModel> getUsersByCondition(UserSearchModel condition);

    AccountModel createUserIncludeAccount(AccountModel accountModel);

    AccountModel updateUserIncludeAccount(AccountModel accountModel);

    Integer inactiveUser(Long accountId);
}
