/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.user;

import lombok.Data;

import java.util.List;

/**
 * @author wanyun
 */
@Data
public class StaffSearchModel extends StaffModel{

    private List<Long> companyIdList;
}
