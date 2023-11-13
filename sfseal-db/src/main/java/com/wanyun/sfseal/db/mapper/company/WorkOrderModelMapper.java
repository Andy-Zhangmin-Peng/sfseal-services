package com.wanyun.sfseal.db.mapper.company;

import com.wanyun.sfseal.db.model.company.WorkOrderModel;
import com.wanyun.sfseal.db.model.company.WorkOrderSearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkOrderModelMapper {

    int deleteByPrimaryKey(Long WorkOrderModelId);

    int insert(WorkOrderModel record);

    int insertSelective(WorkOrderModel record);

    WorkOrderModel selectByPrimaryKey(Long WorkOrderModelId);

    int updateByPrimaryKeySelective(WorkOrderModel record);

    int updateByPrimaryKey(WorkOrderModel record);

    List<WorkOrderModel> getWorkOrdersByCondition(WorkOrderSearchModel condition);

    int deleteByWorkOrderId(@Param("workOrderIdList") List<Long> workOrderIdList);

    WorkOrderModel selectWorkOrderByRFID(String rfid);

    WorkOrderModel getWorkOrderByWayBillNumber(@Param("wayBillNumber") String wayBillNumber);
}