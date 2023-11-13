/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class ExceptionalSituationAttachmentXrefModel extends BaseModel {

    /**
     *  关联关系ID
     */
    private Long xrefId;

    /**
     * 附件ID
     */
    private Long attachmentId;

    /**
     * 异常报告ID
     */
    private Long exceptionSituationId;
}

