package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.ImportRecordSearchModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.ImportRecordModel;
import com.wanyun.sfseal.db.mapper.company.ImportRecordModelMapper;
import com.wanyun.sfseal.company.service.ImportRecordModelService;

import java.util.List;

@Service
public class ImportRecordModelServiceImpl implements ImportRecordModelService{

    @Resource
    private ImportRecordModelMapper importRecordModelMapper;

    @Override
    public int deleteByPrimaryKey(Long importId) {
        return importRecordModelMapper.deleteByPrimaryKey(importId);
    }

    @Override
    public int insert(ImportRecordModel record) {
        return importRecordModelMapper.insert(record);
    }

    @Override
    public int insertSelective(ImportRecordModel record) {
        return importRecordModelMapper.insertSelective(record);
    }

    @Override
    public ImportRecordModel selectByPrimaryKey(Long importId) {
        return importRecordModelMapper.selectByPrimaryKey(importId);
    }

    @Override
    public int updateByPrimaryKeySelective(ImportRecordModel record) {
        return importRecordModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ImportRecordModel record) {
        return importRecordModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ImportRecordModel> getImportRecordByCondition(ImportRecordSearchModel condition) {
        return importRecordModelMapper.getImportRecordByCondition(condition);
    }

}
