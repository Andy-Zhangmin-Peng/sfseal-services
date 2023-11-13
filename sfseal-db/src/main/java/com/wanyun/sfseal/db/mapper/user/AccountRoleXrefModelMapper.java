/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.user;

import com.wanyun.sfseal.db.model.user.AccountRoleXrefModel;

/**
 * @author wanyun
 */
public interface AccountRoleXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(AccountRoleXrefModel record);

    int insertSelective(AccountRoleXrefModel record);

    AccountRoleXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(AccountRoleXrefModel record);

    int updateByPrimaryKey(AccountRoleXrefModel record);

    int upsert(AccountRoleXrefModel accountRoleXrefModel);
}