package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.WorkOrderTransportXrefModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkOrderTransportXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(WorkOrderTransportXrefModel record);

    int insertSelective(WorkOrderTransportXrefModel record);

    WorkOrderTransportXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(WorkOrderTransportXrefModel record);

    int updateByPrimaryKey(WorkOrderTransportXrefModel record);

    int updateBatch(List<WorkOrderTransportXrefModel> list);

    int updateBatchSelective(List<WorkOrderTransportXrefModel> list);

    int batchInsert(@Param("list") List<WorkOrderTransportXrefModel> list);

    int deleteByWorkOrderId(@Param("workOrderIdList") List<Long> workOrderIdList);
}