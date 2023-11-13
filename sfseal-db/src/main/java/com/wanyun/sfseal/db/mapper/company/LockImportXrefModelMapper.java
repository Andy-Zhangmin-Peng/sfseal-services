package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.LockImportXrefModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LockImportXrefModelMapper {
    int deleteByPrimaryKey(Long xrefId);

    int insert(LockImportXrefModel record);

    int insertSelective(LockImportXrefModel record);

    LockImportXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(LockImportXrefModel record);

    int updateByPrimaryKey(LockImportXrefModel record);

    int updateBatch(List<LockImportXrefModel> list);

    int batchInsert(@Param("list") List<LockImportXrefModel> list);

    List<String> getRfidsByImportId(Long importId);
}