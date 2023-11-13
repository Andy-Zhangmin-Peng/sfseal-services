package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.DispenseRecordModel;
import com.wanyun.sfseal.db.model.company.DispenseRecordSearchModel;

import java.util.List;

public interface DispenseRecordModelMapper {
    int deleteByPrimaryKey(Long dispenseRecordId);

    int insert(DispenseRecordModel record);

    int insertSelective(DispenseRecordModel record);

    DispenseRecordModel selectByPrimaryKey(Long dispenseRecordId);

    int updateByPrimaryKeySelective(DispenseRecordModel record);

    int updateByPrimaryKey(DispenseRecordModel record);

    List<DispenseRecordModel> getDispenseRecordsByCondition(DispenseRecordSearchModel condition);

}