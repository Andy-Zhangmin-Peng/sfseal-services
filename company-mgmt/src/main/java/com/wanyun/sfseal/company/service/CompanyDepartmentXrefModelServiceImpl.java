/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.CompanyDepartmentXrefModel;
import com.wanyun.sfseal.db.mapper.company.CompanyDepartmentXrefModelMapper;
import com.wanyun.sfseal.company.service.CompanyDepartmentXrefModelService;

/**
 * This service is the implementation of CompanyDepartmentXrefModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class CompanyDepartmentXrefModelServiceImpl implements CompanyDepartmentXrefModelService{

    @Resource
    private CompanyDepartmentXrefModelMapper companyDepartmentXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return companyDepartmentXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(CompanyDepartmentXrefModel record) {
        return companyDepartmentXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyDepartmentXrefModel record) {
        return companyDepartmentXrefModelMapper.insertSelective(record);
    }

    @Override
    public CompanyDepartmentXrefModel selectByPrimaryKey(Long xrefId) {
        return companyDepartmentXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyDepartmentXrefModel record) {
        return companyDepartmentXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyDepartmentXrefModel record) {
        return companyDepartmentXrefModelMapper.updateByPrimaryKey(record);
    }

}
