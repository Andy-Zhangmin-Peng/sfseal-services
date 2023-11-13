/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.mapper.company.CompanyDepartmentXrefModelMapper;
import com.wanyun.sfseal.db.model.company.CompanyDepartmentXrefModel;
import com.wanyun.sfseal.db.model.company.DepartmentSearchModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.DepartmentModel;
import com.wanyun.sfseal.db.mapper.company.DepartmentModelMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
/**
 * This service is the implementation of DepartmentModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class DepartmentModelServiceImpl extends BaseServiceImpl implements DepartmentModelService{

    @Resource
    private DepartmentModelMapper departmentModelMapper;

    @Resource
    private CompanyDepartmentXrefModelMapper companyDepartmentXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long departmentId) {
        return departmentModelMapper.deleteByPrimaryKey(departmentId);
    }

    @Override
    public int insert(DepartmentModel record) {
        return departmentModelMapper.insert(record);
    }

    @Override
    public int insertSelective(DepartmentModel record) {
        return departmentModelMapper.insertSelective(record);
    }

    @Override
    public DepartmentModel selectByPrimaryKey(Long departmentId) {
        return departmentModelMapper.selectByPrimaryKey(departmentId);
    }

    @Override
    public int updateByPrimaryKeySelective(DepartmentModel record) {
        return departmentModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DepartmentModel record) {
        return departmentModelMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<DepartmentModel> getDepartmentsByCondition(DepartmentSearchModel departmentSearchModel) throws WanYunBusinessException {
        if (departmentSearchModel.getCompanyId() == null){
            departmentSearchModel.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
        }
        return departmentModelMapper.getDepartmentsByCondition(departmentSearchModel);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public DepartmentModel createDepartment(DepartmentModel departmentModel) throws WanYunBusinessException {

        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        departmentModel.setLastUpdateDate(currentTimeStamp);
        departmentModel.setCreateDate(currentTimeStamp);
        departmentModel.setLastUpdateUser(this.getCurrentLoginInfo().getAccountName());

        departmentModelMapper.insert(departmentModel);

        CompanyDepartmentXrefModel companyDepartmentXrefModel = new CompanyDepartmentXrefModel();


        companyDepartmentXrefModel.setDepartmentId(departmentModel.getDepartmentId());
        companyDepartmentXrefModel.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
        companyDepartmentXrefModel.setCreateDate(currentTimeStamp);
        companyDepartmentXrefModel.setLastUpdateDate(currentTimeStamp);
        companyDepartmentXrefModel.setLastUpdateUser(this.getCurrentLoginInfo().getAccountName());

        companyDepartmentXrefModelMapper.insert(companyDepartmentXrefModel);

        return departmentModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public DepartmentModel updateDepartment(DepartmentModel departmentModel) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        departmentModel.setLastUpdateUser(this.getCurrentLoginInfo().getAccountName());
        departmentModel.setLastUpdateDate(currentTimeStamp);

        departmentModelMapper.updateByPrimaryKeySelective(departmentModel);
        return departmentModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException {
        Long companyId = this.getCurrentLoginInfo().getCompanyId();
        int count = departmentModelMapper.checkValueExisting(valueType, value , companyId);
        return count > 0 ? true : false;
    }
}
