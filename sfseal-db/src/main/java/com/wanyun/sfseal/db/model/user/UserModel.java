/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author wanyun
 */
@Data
public class UserModel {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户账号ID
     */
    private Long accountId;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户状态
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
}