package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.wanyun.sfseal.db.mapper.company.LockImportXrefModelMapper;
import com.wanyun.sfseal.db.model.company.LockImportXrefModel;
import com.wanyun.sfseal.company.service.LockImportXrefModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class LockImportXrefModelServiceImpl implements LockImportXrefModelService {

    @Resource
    private LockImportXrefModelMapper lockImportXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return lockImportXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(LockImportXrefModel record) {
        return lockImportXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(LockImportXrefModel record) {
        return lockImportXrefModelMapper.insertSelective(record);
    }

    @Override
    public LockImportXrefModel selectByPrimaryKey(Long xrefId) {
        return lockImportXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(LockImportXrefModel record) {
        return lockImportXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LockImportXrefModel record) {
        return lockImportXrefModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<LockImportXrefModel> list) {
        return lockImportXrefModelMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<LockImportXrefModel> list) {
        return lockImportXrefModelMapper.batchInsert(list);
    }

    @Override
    public List<String> getRfidsByImportId(Long importId) {
        return lockImportXrefModelMapper.getRfidsByImportId(importId);
    }

}

