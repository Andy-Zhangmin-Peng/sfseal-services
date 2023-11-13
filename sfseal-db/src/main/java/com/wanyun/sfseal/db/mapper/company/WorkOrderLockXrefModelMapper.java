package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.WorkOrderLockXrefModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkOrderLockXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(WorkOrderLockXrefModel record);

    int insertSelective(WorkOrderLockXrefModel record);

    WorkOrderLockXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(WorkOrderLockXrefModel record);

    int updateByPrimaryKey(WorkOrderLockXrefModel record);

    void addInbatch(@Param("list") List<WorkOrderLockXrefModel> workOrderLockXrefModels);

    int deleteByWorkOrderId(@Param("workOrderIdList") List<Long> workOrderIdList);
}