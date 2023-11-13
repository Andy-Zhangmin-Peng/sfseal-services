package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.model.company.ImportRecordModel;
import com.wanyun.sfseal.db.model.company.ImportRecordSearchModel;

import java.util.List;

public interface ImportRecordModelService{


    int deleteByPrimaryKey(Long importId);

    int insert(ImportRecordModel record);

    int insertSelective(ImportRecordModel record);

    ImportRecordModel selectByPrimaryKey(Long importId);

    int updateByPrimaryKeySelective(ImportRecordModel record);

    int updateByPrimaryKey(ImportRecordModel record);

    List<ImportRecordModel> getImportRecordByCondition(ImportRecordSearchModel condition);
}
