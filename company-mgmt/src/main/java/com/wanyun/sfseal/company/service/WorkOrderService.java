package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.company.AttachmentModel;
import com.wanyun.sfseal.db.model.company.WorkOrderModel;
import com.wanyun.sfseal.db.model.company.WorkOrderSearchModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkOrderService {


    int deleteByPrimaryKey(Long workOrderId);

    int insert(WorkOrderModel record);

    int insertSelective(WorkOrderModel record);

    WorkOrderModel selectByPrimaryKey(Long workOrderId);

    int updateByPrimaryKeySelective(WorkOrderModel record);

    int updateByPrimaryKey(WorkOrderModel record);

    List<WorkOrderModel> getWorkOrdersByCondition(WorkOrderSearchModel condition);

    /**
     * 创建工单
     * @param workOrderModel
     * @return
     */
    WorkOrderModel createWorkOrder(WorkOrderModel workOrderModel);

    WorkOrderModel updateWorkOrder(WorkOrderModel workOrderModel);

    int removeWorkOrders(List<Long> workOrderIdList);

    WorkOrderModel getWorkOrderByRFID(String rfid);

    WorkOrderModel getWorkOrderByWayBillNumber(String wayBillNumber);

    AttachmentModel uploadWorkOrderAttachment(Long attachmentType, MultipartFile file) throws WanYunBusinessException;
}

