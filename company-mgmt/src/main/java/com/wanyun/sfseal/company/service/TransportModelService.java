package com.wanyun.sfseal.company.service;

import java.util.List;
import com.wanyun.sfseal.db.model.company.TransportModel;
public interface TransportModelService{


    int deleteByPrimaryKey(Long id);

    int insert(TransportModel record);

    int insertSelective(TransportModel record);

    TransportModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransportModel record);

    int updateByPrimaryKey(TransportModel record);

    int updateBatch(List<TransportModel> list);

    int updateBatchSelective(List<TransportModel> list);

    int batchInsert(List<TransportModel> list);

    TransportModel createTransport(String transportId);
}
