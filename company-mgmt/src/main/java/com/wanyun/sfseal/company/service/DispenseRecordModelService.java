package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.DispenseRecordModel;
import com.wanyun.sfseal.db.model.company.DispenseRecordSearchModel;

import java.util.List;

public interface DispenseRecordModelService{


    int deleteByPrimaryKey(Long dispenseRecordId);

    int insert(DispenseRecordModel record);

    int insertSelective(DispenseRecordModel record);

    DispenseRecordModel selectByPrimaryKey(Long dispenseRecordId);

    int updateByPrimaryKeySelective(DispenseRecordModel record);

    int updateByPrimaryKey(DispenseRecordModel record);

    List<DispenseRecordModel> getDispenseByCondition(DispenseRecordSearchModel condition);

    int rollbackDispenseLocks(Long dispenseRecordId);

    int redispense(DispenseRecordModel dispenseRecord);

    int receive(DispenseRecordModel dispenseRecord);
}
