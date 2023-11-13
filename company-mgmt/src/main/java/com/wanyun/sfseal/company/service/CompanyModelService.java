/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.company.*;

import java.util.List;

/**
 * This service is the interface of CompanyModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface CompanyModelService{


    int deleteByPrimaryKey(Long companyId);

    int insert(CompanyModel record);

    int insertSelective(CompanyModel record);

    CompanyModel selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(CompanyModel record);

    int updateByPrimaryKey(CompanyModel record);

    /**
     * 查找所有公司信息
     * @return List<CompanyModel>
     * @throws WanYunBusinessException
     */
    List<CompanyModel> getAllcompany() throws WanYunBusinessException;

    /**
     * 根据条件查找公司
     * @param condition
     * @return List<CompanyModel>
     */
    List<CompanyModel> getCompaniesByCondition(CompanySearchModel condition);

    /**
     * 创建公司
     * @param company
     * @return CompanyModel
     */
    CompanyModel createCompany(CompanyModel company);

    /**
     * 更新公司
     * @param company
     * @return
     */
    CompanyModel updateCompany(CompanyModel company);

    /**
     * 检查字断值是否已经存在
     * @param valueType
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException;

    List<InventoryModel> getAllInventoryByCondition(InventorySearchModel condition);

    InventoryModel getCurrentyUserInventory();
}
