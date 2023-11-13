/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class WorkOrderSearchModel extends WorkOrderModel {
    private List<Long> workOrderStatusIdList;

    private String startAddress;

    private String endAddress;
}
