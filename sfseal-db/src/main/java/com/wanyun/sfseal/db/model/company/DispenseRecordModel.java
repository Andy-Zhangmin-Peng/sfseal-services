package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class DispenseRecordModel extends BaseModel {
    /**
    * 分发记录ID
    */
    private Long dispenseRecordId;

    /**
    * 公司ID
    */
    private Long companyId;

    /**
    * 分发数量
    */
    private Long dispenseCount;

    /**
     *
     */
    private Boolean isActive;

    /**
     * 分发给的公司名
     */
    private String companyName;

    /**
     * 是否已经接收
     */
    private Boolean accpeted;

}