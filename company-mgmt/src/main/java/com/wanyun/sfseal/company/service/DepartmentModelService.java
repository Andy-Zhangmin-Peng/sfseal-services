/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.company.DepartmentModel;
import com.wanyun.sfseal.db.model.company.DepartmentSearchModel;

import java.util.List;
/**
 * This service is the interface of DepartmentModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface DepartmentModelService{


    int deleteByPrimaryKey(Long departmentId);

    int insert(DepartmentModel record);

    int insertSelective(DepartmentModel record);

    DepartmentModel selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(DepartmentModel record);

    int updateByPrimaryKey(DepartmentModel record);

    /**
     * 根据不同条件查找部门信息
     *
     * @param departmentSearchModel
     * @return
     * @throws WanYunBusinessException
     */
    List<DepartmentModel> getDepartmentsByCondition(DepartmentSearchModel departmentSearchModel) throws WanYunBusinessException;

    /**
     * 创建部门
     *
     * @param departmentModel
     * @return
     * @throws WanYunBusinessException
     */
    DepartmentModel createDepartment(DepartmentModel departmentModel) throws WanYunBusinessException;

    /**
     * 更新部门
     *
     * @param departmentModel
     * @return
     * @throws WanYunBusinessException
     */
    DepartmentModel updateDepartment(DepartmentModel departmentModel) throws WanYunBusinessException;

    /**
     * 检查字断值是否存在
     *
     * @param valueType
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException;
}
