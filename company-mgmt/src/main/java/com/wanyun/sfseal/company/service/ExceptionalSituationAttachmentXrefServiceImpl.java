package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.ExceptionalSituationAttachmentXrefModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.wanyun.sfseal.db.mapper.company.ExceptionalSituationAttachmentXrefMapper;
@Service
public class ExceptionalSituationAttachmentXrefServiceImpl implements ExceptionalSituationAttachmentXrefService{

    @Resource
    private ExceptionalSituationAttachmentXrefMapper exceptionalSituationAttachmentXrefMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return exceptionalSituationAttachmentXrefMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(ExceptionalSituationAttachmentXrefModel record) {
        return exceptionalSituationAttachmentXrefMapper.insert(record);
    }

    @Override
    public int insertSelective(ExceptionalSituationAttachmentXrefModel record) {
        return exceptionalSituationAttachmentXrefMapper.insertSelective(record);
    }

    @Override
    public ExceptionalSituationAttachmentXrefModel selectByPrimaryKey(Long xrefId) {
        return exceptionalSituationAttachmentXrefMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(ExceptionalSituationAttachmentXrefModel record) {
        return exceptionalSituationAttachmentXrefMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExceptionalSituationAttachmentXrefModel record) {
        return exceptionalSituationAttachmentXrefMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<ExceptionalSituationAttachmentXrefModel> list) {
        return exceptionalSituationAttachmentXrefMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<ExceptionalSituationAttachmentXrefModel> list) {
        return exceptionalSituationAttachmentXrefMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ExceptionalSituationAttachmentXrefModel> list) {
        return exceptionalSituationAttachmentXrefMapper.batchInsert(list);
    }

}
