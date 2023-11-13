/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;


import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;
/**
 * @author wanyun
 */
@Data
public class CompanyDepartmentXrefModel extends BaseModel {
    /**
     * 关系ID
     */
    private Long xrefId;

    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 公司ID
     */
    private Long companyId;
}