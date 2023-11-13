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
public class WorkOrderTransportXrefModel extends BaseModel {
    /**
    * xrefId
    */
    private Long xrefId;

    /**
    * workOrderId
    */
    private Long workOrderId;

    /**
    * tranId
    */
    private Long tranId;

    private Boolean isActive;
}