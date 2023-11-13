/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import com.wanyun.sfseal.db.model.company.CompanyModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanyun
 */
@Data
public class StaffModel {
    /**
     * 所属账户ID
     */
    private Long accountId;

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 管理员手机号
     */
    private String mobile;

    /**
     * 管理员办公电话
     */
    private String officePhoneNumber;

    /**
     * 管理员地址
     */
    private String address;

    /**
     * 账户状态
     */
    private Boolean isActive;

    /**
     * 创建时间
     */
    private Timestamp createDate;

    /**
     * 上次更新时间
     */
    private Timestamp lastUpdateDate;

    /**
     * 上次更新人
     */
    private String lastUpdateUser;

    private CompanyModel company;

    private String companyName;
}