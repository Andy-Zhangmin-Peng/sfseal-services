package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.WorkOrderAddressModel;

public interface WorkOrderAddressModelService {


    int deleteByPrimaryKey(Long addressId);

    int insert(WorkOrderAddressModel record);

    int insertSelective(WorkOrderAddressModel record);

    WorkOrderAddressModel selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(WorkOrderAddressModel record);

    int updateByPrimaryKey(WorkOrderAddressModel record);

    int updateBatch(List<WorkOrderAddressModel> list);

    int batchInsert(List<WorkOrderAddressModel> list);

}


