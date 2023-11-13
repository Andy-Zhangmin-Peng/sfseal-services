/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ImportRecordSearchModel extends ImportRecordModel {
    private Timestamp importStartTime;

    private Timestamp importEndTime;
}
