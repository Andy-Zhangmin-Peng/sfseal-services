/*
 * Copyright 2018 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author WanYun
 */

@Data
@EqualsAndHashCode
@ToString
public class ImportConfigureModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String displayName;
    private String fieldName;
    private String valueType;
    private boolean required;
    private String valueFormat;
}
