/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.db.model.reference;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author WanYun
 */
@Data
@EqualsAndHashCode
public class ReferenceDataModel implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 语言
     */
    private Integer language;

    /**
     * 类型
     */
    private String category;

    /**
     * 关键字
     */
    private String key;

    /**
     * 显示key
     */
    private String displayKey;

    /**
     * 显示顺序
     */
    private Integer displayOrder;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否有效
     */
    private Boolean isActive;

    /**
     * 其它数据
     */
    private Object attributes;

    /**
     * 修改时间
     */
    private Timestamp lastUpdateDate;

}
