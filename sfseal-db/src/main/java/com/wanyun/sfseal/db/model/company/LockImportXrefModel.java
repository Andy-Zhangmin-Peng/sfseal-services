package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class LockImportXrefModel extends BaseModel {
    /**
     * 关系ID
     */
    private Long xrefId;

    /**
     * 电子封锁rfid
     */
    private String rfid;

    /**
     * 导入记录ID
     */
    private Long importId;
}