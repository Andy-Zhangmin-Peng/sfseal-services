package com.wanyun.sfseal.db.model.company;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

@Data
public class WorkOrderModel extends BaseModel {
    /**
     * 工单编号
     */
    private Long workOrderId;

    /**
     * 所属公司ID
     */
    private Long companyId;

    /**
     * 运单状态码
     */
    private Long statusCode;

    /**
     * 货主姓名
     */
    private String owner;

    /**
     * 承运人
     */
    private String carriera;

    /**
     * 订单开始时间
     */
    private Timestamp startTime;

    /**
     * 订单结束时间
     */
    private Timestamp endTime;

    /**
     * 货物明细
     */
    private String productDetail;

    /**
     * 运单附件
     */
    private Long attachmentId;

    private String statusName;

    /**
     * 临时承运人
     */
    private String tempCarriera;

    /**
     * 运单号
     */
    private String wayBillNumber;

    private List<WorkOrderAddressModel> addressList;

    private List<LockModel> lockList;

    private List<TransportModel> transportList;

    private AttachmentModel attachment;

}
