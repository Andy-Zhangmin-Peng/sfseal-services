/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.CompanyDepartmentXrefModel;

/**
 * This service is the interface of CompanyDepartmentXrefModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface CompanyDepartmentXrefModelService{


    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyDepartmentXrefModel record);

    int insertSelective(CompanyDepartmentXrefModel record);

    CompanyDepartmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyDepartmentXrefModel record);

    int updateByPrimaryKey(CompanyDepartmentXrefModel record);

}
