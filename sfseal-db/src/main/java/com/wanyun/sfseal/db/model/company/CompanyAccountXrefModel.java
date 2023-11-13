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
public class CompanyAccountXrefModel extends BaseModel {
    /**
     * 关系ID
     */
    private Long xrefId;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 账户ID
     */
    private Long accountId;
}