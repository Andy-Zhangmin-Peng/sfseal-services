/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.CompanyLockXrefModel;

import java.util.List;

/**
 * This service is the interface of CompanyLockXrefModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface CompanyLockXrefModelService {


    int deleteByPrimaryKey(Long xrefId);

    int insert(CompanyLockXrefModel record);

    int insertSelective(CompanyLockXrefModel record);

    CompanyLockXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(CompanyLockXrefModel record);

    int updateByPrimaryKey(CompanyLockXrefModel record);

    List<String> getAllRfidsByDispenseRecordId(Long dispenseRecordId);
}

