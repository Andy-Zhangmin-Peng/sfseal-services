package com.wanyun.sfseal.db.model.company;

import java.util.Date;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class AttachmentModel extends BaseModel {
    /**
     * 附件编号
     */
    private Long attachmentId;

    /**
     * 附件Base64 Code
     */
    private String attachmentCode;

    /**
     * 附件类型
     */
    private Long attachmentType;

    /**
     * 附件名称
     */
    private String attachmentName;
}