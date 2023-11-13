package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.wanyun.sfseal.db.model.company.WorkOrderAddressModel;
import com.wanyun.sfseal.db.mapper.company.WorkOrderAddressModelMapper;
import com.wanyun.sfseal.company.service.WorkOrderAddressModelService;

@Service
public class WorkOrderAddressModelServiceImpl implements WorkOrderAddressModelService {

    @Resource
    private WorkOrderAddressModelMapper workOrderAddressModelMapper;

    @Override
    public int deleteByPrimaryKey(Long addressId) {
        return workOrderAddressModelMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    public int insert(WorkOrderAddressModel record) {
        return workOrderAddressModelMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkOrderAddressModel record) {
        return workOrderAddressModelMapper.insertSelective(record);
    }

    @Override
    public WorkOrderAddressModel selectByPrimaryKey(Long addressId) {
        return workOrderAddressModelMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrderAddressModel record) {
        return workOrderAddressModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrderAddressModel record) {
        return workOrderAddressModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<WorkOrderAddressModel> list) {
        return workOrderAddressModelMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<WorkOrderAddressModel> list) {
        return workOrderAddressModelMapper.batchInsert(list);
    }

}


