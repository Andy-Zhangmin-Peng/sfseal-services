/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;


import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class CompanyLockXrefModel extends BaseModel {
    /**
     * xrefId
     */
    private Long xrefId;

    /**
     * companyId
     */
    private Long companyId;

    /**
     * rfid
     */
    private String rfid;

    /**
     * isActive
     */
    private Boolean isActive;

    private Long dispenseRecordId;
}