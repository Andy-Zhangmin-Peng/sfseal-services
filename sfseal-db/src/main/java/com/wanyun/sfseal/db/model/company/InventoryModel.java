/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InventoryModel {
    private String companyName;

    private int latestPurchaseCount;

    private int unusedCount;

    private int usedCount;

    private int purchaseCount;

    private Timestamp latestPurchaseDate;
}
