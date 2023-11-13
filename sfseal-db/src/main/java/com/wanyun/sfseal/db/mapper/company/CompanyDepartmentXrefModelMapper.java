/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.CompanyDepartmentXrefModel;

/**
 * @author wanyun
 */
public interface CompanyDepartmentXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyDepartmentXrefModel record);

    int insertSelective(CompanyDepartmentXrefModel record);

    CompanyDepartmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyDepartmentXrefModel record);

    int updateByPrimaryKey(CompanyDepartmentXrefModel record);
}