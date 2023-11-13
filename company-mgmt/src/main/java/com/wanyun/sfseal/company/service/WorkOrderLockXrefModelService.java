package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.WorkOrderLockXrefModel;
public interface WorkOrderLockXrefModelService{


    int deleteByPrimaryKey(Long xrefId);

    int insert(WorkOrderLockXrefModel record);

    int insertSelective(WorkOrderLockXrefModel record);

    WorkOrderLockXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(WorkOrderLockXrefModel record);

    int updateByPrimaryKey(WorkOrderLockXrefModel record);

}
