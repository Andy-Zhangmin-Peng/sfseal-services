/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.user.AccountModel;
import com.wanyun.sfseal.db.model.user.StaffModel;
import com.wanyun.sfseal.db.model.user.StaffSearchModel;

import java.util.List;

/**
 * This service is the interface of StaffModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface StaffModelService {

    int deleteByPrimaryKey(Long accountId);

    int insert(StaffModel record);

    int insertSelective(StaffModel record);

    StaffModel selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(StaffModel record);

    int updateByPrimaryKey(StaffModel record);

    List<StaffModel> getStaffsByCondition(StaffSearchModel condition);

    AccountModel updateStaffIncludeAccount(AccountModel accountModel) throws WanYunBusinessException;

    AccountModel createStaffIncludeAccount(AccountModel accountModel) throws WanYunBusinessException;

    int inactiveStaff(Long accountId) throws WanYunBusinessException;

}
