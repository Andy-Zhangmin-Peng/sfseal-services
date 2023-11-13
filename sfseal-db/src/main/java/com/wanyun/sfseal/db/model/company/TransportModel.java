/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */
package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wanyun
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransportModel extends BaseModel {
    /**
     * id
     */
    private Long id;

    /**
     * 运输载体ID
     */
    private String transprortId;

    /**
     * 运输载体服务过的公司ID
     */
    private Long companyId;
}