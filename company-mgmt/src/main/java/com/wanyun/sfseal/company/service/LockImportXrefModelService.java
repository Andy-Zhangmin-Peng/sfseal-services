package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.LockImportXrefModel;

public interface LockImportXrefModelService {


    int deleteByPrimaryKey(Long xrefId);

    int insert(LockImportXrefModel record);

    int insertSelective(LockImportXrefModel record);

    LockImportXrefModel selectByPrimaryKey(Long xrefId);

    int updateByPrimaryKeySelective(LockImportXrefModel record);

    int updateByPrimaryKey(LockImportXrefModel record);

    int updateBatch(List<LockImportXrefModel> list);

    int batchInsert(List<LockImportXrefModel> list);

    List<String> getRfidsByImportId(Long importId);
}

