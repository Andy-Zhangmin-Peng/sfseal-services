package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class LockModel extends BaseModel {
    /**
     * 芯片号
     */
    private String rfid;

    /**
     * 盒条码
     */
    private String boxId;

    /**
     * 条形码号
     */
    private String barCode;

    /**
     * 状态码
     */
    private Long statusCode;

    /**
     * 所属公司名
     */
    private String companyName;

    /**
     * 所属公司ID
     */
    private Long companyId;

    /**
     * 状态名称
     */
    private String statusName;

    /**
     * 锁定位置
     */
    private String lockPosition;

    /**
     * 实际运输载体ID
     */
    private Long tranId;

    /**
     * 施封用户ID
     */
    private Long lockUserId;

    /**
     * 拆封用户ID
     */
    private Long unlockUserId;

    /**
     *  实际车船载体，仅供施封过程安卓端使用
     */
    private String transprortId;

    /**
     * 施封和拆封时照片附件
     */
    private List<AttachmentModel> attachmentModelList;

    /**
     * 施封账户名
     */
    private String lockAccountName;

    /**
     * 拆封账户名
     */
    private String unLockAccountName;

    /**
     * 车船号
     */
    private String transportName;

}
