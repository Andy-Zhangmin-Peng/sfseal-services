/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ExceptionalSituationSearchModel extends ExceptionalSituationModel {
    private Timestamp reportStartTime;

    private Timestamp reportEndTime;

    private List<Long> typeList;

}
