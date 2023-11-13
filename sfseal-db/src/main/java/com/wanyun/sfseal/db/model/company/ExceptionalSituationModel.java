package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionalSituationModel extends BaseModel {
    /**
     * 异常编号
     */
    private Long exceptionId;

    /**
     * 异常级别
     */
    private Long exceptionLevel;

    /**
     * 异常类型
     */
    private Long exceptionTypeCode;

    /**
     * 异常描述
     */
    private String exceptionMessage;

    /**
     * 上报异常地址ID
     */
    private Long reportAddressId;

    /**
     * 上报异常人ID
     */
    private Long reportUserId;

    /**
     * 电子封锁序列号
     */
    private String barCode;

    /**
     * 工单编号
     */
    private Long workOrderId;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 车船号
     */
    private String tranId;

    /**
     * 异常类型名（仅供查询使用)
     */
    private String exceptionTypeName;

    /**
     * 地址详情 (仅供查询使用)
     */
    private String addressDetail;

    /**
     * 图片附件ID list
     */
    private List<Long> attachmentIds;

    /**
     * 上报异常时的位置
     */
    private WorkOrderAddressModel addressModel;

    /**
     * 异常照片附件
     */
    private List<AttachmentModel> attachmentList;
}
