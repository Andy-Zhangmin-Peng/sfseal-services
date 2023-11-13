/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

/**
 * This service is the interface of CompanyAccountXrefModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
import com.wanyun.sfseal.db.model.company.CompanyAccountXrefModel;
public interface CompanyAccountXrefModelService{


    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyAccountXrefModel record);

    int insertSelective(CompanyAccountXrefModel record);

    CompanyAccountXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyAccountXrefModel record);

    int updateByPrimaryKey(CompanyAccountXrefModel record);

}
