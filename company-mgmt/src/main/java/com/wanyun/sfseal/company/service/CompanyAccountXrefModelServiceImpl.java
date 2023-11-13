/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.mapper.company.CompanyAccountXrefModelMapper;
import com.wanyun.sfseal.db.model.company.CompanyAccountXrefModel;
import com.wanyun.sfseal.company.service.CompanyAccountXrefModelService;

/**
 * This service is the implementation of CompanyAccountXrefModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class CompanyAccountXrefModelServiceImpl implements CompanyAccountXrefModelService{

    @Resource
    private CompanyAccountXrefModelMapper companyAccountXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return companyAccountXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(CompanyAccountXrefModel record) {
        return companyAccountXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyAccountXrefModel record) {
        return companyAccountXrefModelMapper.insertSelective(record);
    }

    @Override
    public CompanyAccountXrefModel selectByPrimaryKey(Long xrefId) {
        return companyAccountXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyAccountXrefModel record) {
        return companyAccountXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyAccountXrefModel record) {
        return companyAccountXrefModelMapper.updateByPrimaryKey(record);
    }

}
