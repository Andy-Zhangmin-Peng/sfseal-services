package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.wanyun.sfseal.db.model.company.WorkOrderTransportXrefModel;
import com.wanyun.sfseal.db.mapper.company.WorkOrderTransportXrefModelMapper;
import com.wanyun.sfseal.company.service.WorkOrderTransportXrefModelService;
@Service
public class WorkOrderTransportXrefModelServiceImpl implements WorkOrderTransportXrefModelService{

    @Resource
    private WorkOrderTransportXrefModelMapper workOrderTransportXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return workOrderTransportXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(WorkOrderTransportXrefModel record) {
        return workOrderTransportXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkOrderTransportXrefModel record) {
        return workOrderTransportXrefModelMapper.insertSelective(record);
    }

    @Override
    public WorkOrderTransportXrefModel selectByPrimaryKey(Long xrefId) {
        return workOrderTransportXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrderTransportXrefModel record) {
        return workOrderTransportXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrderTransportXrefModel record) {
        return workOrderTransportXrefModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<WorkOrderTransportXrefModel> list) {
        return workOrderTransportXrefModelMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<WorkOrderTransportXrefModel> list) {
        return workOrderTransportXrefModelMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<WorkOrderTransportXrefModel> list) {
        return workOrderTransportXrefModelMapper.batchInsert(list);
    }

}
