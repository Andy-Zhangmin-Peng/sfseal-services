package com.wanyun.sfseal.db.model.company;

import java.util.Date;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class LockAttachmentXrefModel extends BaseModel {
    /**
     * 关联关系ID
     */
    private Long xrefId;

    /**
     * 电子封锁RFID
     */
    private String rfid;

    /**
     * 附件ID
     */
    private Long attachmentId;
}