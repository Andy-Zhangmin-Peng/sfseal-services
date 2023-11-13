/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wanyun
 */
@Data
public class DepartmentModel extends BaseModel {
    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空")
    private String departmentName;

    /**
     * 部门电话
     */
    private String departmentPhoneNumber;

    /**
     * 部门负责人
     */
    private String departmentAdministrator;

    /**
     * 部门所在公司
     */
    private CompanyModel companyModel;
}