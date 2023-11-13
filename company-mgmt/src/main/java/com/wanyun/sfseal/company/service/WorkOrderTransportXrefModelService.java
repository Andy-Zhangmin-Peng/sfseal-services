package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.WorkOrderTransportXrefModel;
public interface WorkOrderTransportXrefModelService{


    int deleteByPrimaryKey(Long xrefId);

    int insert(WorkOrderTransportXrefModel record);

    int insertSelective(WorkOrderTransportXrefModel record);

    WorkOrderTransportXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(WorkOrderTransportXrefModel record);

    int updateByPrimaryKey(WorkOrderTransportXrefModel record);

    int updateBatch(List<WorkOrderTransportXrefModel> list);

    int updateBatchSelective(List<WorkOrderTransportXrefModel> list);

    int batchInsert(List<WorkOrderTransportXrefModel> list);

}
