/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import lombok.Data;

import java.util.List;

/**
 * @author wanyun
 */
@Data
public class LockSearchModel extends LockModel {
    private List<Long> companyIdList;

    private List<Long> lockStatusIdList;
}
