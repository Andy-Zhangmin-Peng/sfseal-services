package com.wanyun.sfseal.db.mapper.company;

import java.util.List;

import com.wanyun.sfseal.db.model.company.ExceptionalSituationAttachmentXrefModel;
import org.apache.ibatis.annotations.Param;

public interface ExceptionalSituationAttachmentXrefMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(ExceptionalSituationAttachmentXrefModel record);

    int insertSelective(ExceptionalSituationAttachmentXrefModel record);

    ExceptionalSituationAttachmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(ExceptionalSituationAttachmentXrefModel record);

    int updateByPrimaryKey(ExceptionalSituationAttachmentXrefModel record);

    int updateBatch(List<ExceptionalSituationAttachmentXrefModel> list);

    int updateBatchSelective(List<ExceptionalSituationAttachmentXrefModel> list);

    int batchInsert(@Param("list") List<ExceptionalSituationAttachmentXrefModel> list);
}