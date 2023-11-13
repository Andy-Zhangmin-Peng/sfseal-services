package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;
import org.postgresql.geometric.PGpoint;

/**
 * @author andy
 */
@Data
public class WorkOrderAddressModel extends BaseModel {
    /**
     * 运单地址ID
     */
    private Long addressId;

    /**
     * 运单ID
     */
    private Long workOrderId;

    private PGpoint lngLat;

    private Long addressType;

    private String addressDetail;
}
