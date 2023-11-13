/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.StaffModel;
import com.wanyun.sfseal.db.model.user.StaffSearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyun
 */
public interface StaffModelMapper {
    int deleteByPrimaryKey(Long accountId);

    int insert(StaffModel record);

    int insertSelective(StaffModel record);

    StaffModel selectByPrimaryKey(Long accountId);

    int updateByPrimaryKeySelective(StaffModel record);

    int updateByPrimaryKey(StaffModel record);

    StaffModel selectStaffByAccountId(@Param("accountId") Long accountId, @Param("isActive") boolean isActive);

    List<StaffModel> getStaffsByCondition(StaffSearchModel condition);
}