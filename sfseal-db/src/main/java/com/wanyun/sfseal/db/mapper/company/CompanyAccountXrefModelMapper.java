/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.CompanyAccountXrefModel;

/**
 * @author wanyun
 */
public interface CompanyAccountXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyAccountXrefModel record);

    int insertSelective(CompanyAccountXrefModel record);

    CompanyAccountXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyAccountXrefModel record);

    int updateByPrimaryKey(CompanyAccountXrefModel record);
}