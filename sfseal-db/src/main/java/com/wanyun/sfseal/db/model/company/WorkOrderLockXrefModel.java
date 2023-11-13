package com.wanyun.sfseal.db.model.company;

import java.util.Date;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wanyun
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WorkOrderLockXrefModel extends BaseModel {
    /**
    * 关联关系ID
    */
    private Long xrefId;

    /**
    * 工单ID
    */
    private Long workOrderId;

    /**
    * 电子封锁序列号
    */
    private String barCode;

    private Boolean isActive;
}