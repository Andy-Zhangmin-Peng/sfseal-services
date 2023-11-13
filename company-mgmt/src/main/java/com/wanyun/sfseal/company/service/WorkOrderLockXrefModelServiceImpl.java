package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.mapper.company.WorkOrderLockXrefModelMapper;
import com.wanyun.sfseal.db.model.company.WorkOrderLockXrefModel;
import com.wanyun.sfseal.company.service.WorkOrderLockXrefModelService;
@Service
public class WorkOrderLockXrefModelServiceImpl implements WorkOrderLockXrefModelService{

    @Resource
    private WorkOrderLockXrefModelMapper workOrderLockXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return workOrderLockXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(WorkOrderLockXrefModel record) {
        return workOrderLockXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkOrderLockXrefModel record) {
        return workOrderLockXrefModelMapper.insertSelective(record);
    }

    @Override
    public WorkOrderLockXrefModel selectByPrimaryKey(Long xrefId) {
        return workOrderLockXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrderLockXrefModel record) {
        return workOrderLockXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrderLockXrefModel record) {
        return workOrderLockXrefModelMapper.updateByPrimaryKey(record);
    }

}
