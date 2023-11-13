package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.TransportModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransportModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransportModel record);

    int insertSelective(TransportModel record);

    TransportModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransportModel record);

    int updateByPrimaryKey(TransportModel record);

    int updateBatch(List<TransportModel> list);

    int updateBatchSelective(List<TransportModel> list);

    int batchInsert(@Param("list") List<TransportModel> list);

    List<TransportModel> selectTransportsByWorkOrderId (Long workOrderId);

    int upsert(TransportModel transportModel);

    TransportModel getTransportByTranId(@Param("transportId") String transportId , @Param("companyId") Long companyId);
}