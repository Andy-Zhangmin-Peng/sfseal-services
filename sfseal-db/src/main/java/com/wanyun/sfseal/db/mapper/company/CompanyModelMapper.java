/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.CompanyModel;
import com.wanyun.sfseal.db.model.company.CompanySearchModel;
import com.wanyun.sfseal.db.model.company.InventoryModel;
import com.wanyun.sfseal.db.model.company.InventorySearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyun
 */
public interface CompanyModelMapper {
    int deleteByPrimaryKey(Long companyId);

    int insert(CompanyModel record);

    int insertSelective(CompanyModel record);

    CompanyModel selectByPrimaryKey(Long companyId);

    int updateByPrimaryKeySelective(CompanyModel record);

    int updateByPrimaryKey(CompanyModel record);

    List<CompanyModel> selectAll();

    CompanyModel selectCompanyByAccountId(Long accountId);

    List<CompanyModel> getCompaniesByCondition(CompanySearchModel condition);

    int checkValueExisting(@Param("valueType") String valueType, @Param("value") String value);

    List<InventoryModel> getAllInventoryByCondition(InventorySearchModel condition);

    InventoryModel getCurrentyUserInventory(Long companyId);
}