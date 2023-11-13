package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.LockAttachmentXrefModel;

public interface LockAttachmentXrefModelService {


    int deleteByPrimaryKey(Long xrefId);

    int insert(LockAttachmentXrefModel record);

    int insertSelective(LockAttachmentXrefModel record);

    LockAttachmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(LockAttachmentXrefModel record);

    int updateByPrimaryKey(LockAttachmentXrefModel record);

    int updateBatch(List<LockAttachmentXrefModel> list);

    int updateBatchSelective(List<LockAttachmentXrefModel> list);

    int batchInsert(List<LockAttachmentXrefModel> list);

}

