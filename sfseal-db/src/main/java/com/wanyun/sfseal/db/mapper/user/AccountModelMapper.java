/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.AccountModel;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanyun
 */
public interface AccountModelMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(AccountModel record);

    int insertSelective(AccountModel record);

    AccountModel selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(AccountModel record);

    int updateByPrimaryKey(AccountModel record);

    AccountModel getFullAccountByUserInfo(String username);

    AccountModel getFullAccountById(Long accountId);

    int checkValueExisting(@Param("valueType") String valueType, @Param("value") String value);
}