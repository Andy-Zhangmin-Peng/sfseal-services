/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.DepartmentModel;
import com.wanyun.sfseal.db.model.company.DepartmentSearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyun
 */
public interface DepartmentModelMapper {
    int deleteByPrimaryKey(Long departmentId);

    int insert(DepartmentModel record);

    int insertSelective(DepartmentModel record);

    DepartmentModel selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(DepartmentModel record);

    int updateByPrimaryKey(DepartmentModel record);

    List<DepartmentModel> getDepartmentsByCondition(DepartmentSearchModel departmentSearchModel);

    int checkValueExisting(@Param("valueType") String valueType, @Param("value") String value, @Param("companyId") Long companyId);
}