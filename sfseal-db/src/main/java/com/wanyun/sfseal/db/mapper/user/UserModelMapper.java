/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.UserModel;
import com.wanyun.sfseal.db.model.user.UserSearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyun
 */
public interface UserModelMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    UserModel selectBasicUserByAccountId(@Param("accountId") Long accountId);

    List<UserModel> getUsersByCondition(UserSearchModel condition);

    int inactiveUserByAccountId(UserModel userModel);
}