package com.wanyun.sfseal.db.common.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseModel {

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
