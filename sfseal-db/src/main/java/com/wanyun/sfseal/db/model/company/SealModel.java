/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

@Data
public class SealModel {

    private WorkOrderModel workOrder;

    private LockModel lock;
}
