package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.LockAttachmentXrefModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LockAttachmentXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(LockAttachmentXrefModel record);

    int insertSelective(LockAttachmentXrefModel record);

    LockAttachmentXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(LockAttachmentXrefModel record);

    int updateByPrimaryKey(LockAttachmentXrefModel record);

    int updateBatch(List<LockAttachmentXrefModel> list);

    int updateBatchSelective(List<LockAttachmentXrefModel> list);

    int batchInsert(@Param("list") List<LockAttachmentXrefModel> list);
}