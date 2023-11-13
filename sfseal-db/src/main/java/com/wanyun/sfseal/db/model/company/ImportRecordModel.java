package com.wanyun.sfseal.db.model.company;

import com.wanyun.sfseal.db.common.model.BaseModel;
import lombok.Data;

import java.util.List;

@Data
public class ImportRecordModel extends BaseModel {
    /**
     * 导入编号
     */
    private Long importId;

    /**
     * 导入数量
     */
    private Long importCount;

    /**
     * 导入记录对应的电子封锁
     */
    private List<LockModel> lockModelList;
}