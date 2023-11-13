/*
 * Copyright 2018 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author wanyun
 */

@Data
@EqualsAndHashCode
@ToString
public class ImportResultModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int importedRecordCount;
    private int insertedRecordCount;
    private int existedRecordCount;
    private int duplicatedRecordCount;
}
