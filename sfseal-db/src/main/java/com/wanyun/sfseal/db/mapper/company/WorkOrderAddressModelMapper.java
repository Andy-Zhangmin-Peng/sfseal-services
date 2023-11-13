package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.WorkOrderAddressModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkOrderAddressModelMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(WorkOrderAddressModel record);

    int insertSelective(WorkOrderAddressModel record);

    WorkOrderAddressModel selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(WorkOrderAddressModel record);

    int updateByPrimaryKey(WorkOrderAddressModel record);

    int updateBatch(List<WorkOrderAddressModel> list);

    int batchInsert(@Param("list") List<WorkOrderAddressModel> list);

    List<WorkOrderAddressModel> selectWorkOrderAddressByOrderId(Long workOrderId);

    int deleteByWorkOrderId(@Param("workOrderIdList") List<Long> workOrderIdList);

    WorkOrderAddressModel getAddressByWorkorderIdAndAddressType(@Param("workOrderId") Long workOrderId, @Param("addressType") Long addressType);
}
