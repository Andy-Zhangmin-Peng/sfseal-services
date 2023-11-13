package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.wanyun.sfseal.db.model.company.LockAttachmentXrefModel;
import com.wanyun.sfseal.db.mapper.company.LockAttachmentXrefModelMapper;
import com.wanyun.sfseal.company.service.LockAttachmentXrefModelService;

@Service
public class LockAttachmentXrefModelServiceImpl implements LockAttachmentXrefModelService {

    @Resource
    private LockAttachmentXrefModelMapper lockAttachmentXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return lockAttachmentXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(LockAttachmentXrefModel record) {
        return lockAttachmentXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(LockAttachmentXrefModel record) {
        return lockAttachmentXrefModelMapper.insertSelective(record);
    }

    @Override
    public LockAttachmentXrefModel selectByPrimaryKey(Long xrefId) {
        return lockAttachmentXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(LockAttachmentXrefModel record) {
        return lockAttachmentXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LockAttachmentXrefModel record) {
        return lockAttachmentXrefModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<LockAttachmentXrefModel> list) {
        return lockAttachmentXrefModelMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<LockAttachmentXrefModel> list) {
        return lockAttachmentXrefModelMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<LockAttachmentXrefModel> list) {
        return lockAttachmentXrefModelMapper.batchInsert(list);
    }

}

