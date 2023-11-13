package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.ExceptionalSituationAttachmentXrefModel;

public interface ExceptionalSituationAttachmentXrefService{


    int deleteByPrimaryKey(Long xrefId);

    int insert(ExceptionalSituationAttachmentXrefModel record);

    int insertSelective(ExceptionalSituationAttachmentXrefModel record);

    ExceptionalSituationAttachmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(ExceptionalSituationAttachmentXrefModel record);

    int updateByPrimaryKey(ExceptionalSituationAttachmentXrefModel record);

    int updateBatch(List<ExceptionalSituationAttachmentXrefModel> list);

    int updateBatchSelective(List<ExceptionalSituationAttachmentXrefModel> list);

    int batchInsert(List<ExceptionalSituationAttachmentXrefModel> list);

}
