/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InventorySearchModel extends InventoryModel{

    private Timestamp purchaseStartDate;

    private Timestamp purchaseEndate;

    private int unusedCountFrom;

    private int unusedCountTo;

}
