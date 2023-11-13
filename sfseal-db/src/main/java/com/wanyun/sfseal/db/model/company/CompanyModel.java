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
public class CompanyModel extends BaseModel {
    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 企业名称
     */
    @NotNull(message = "企业名称不能为空")
    private String companyName;

    /**
     * 企业电话
     */
    @NotNull(message = "企业电话不能为空")
    private String phoneNumber;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 企业邮箱
     */
    private String email;
}