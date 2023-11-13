package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.common.util.ImageConvertIntoBase64Utils;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.mapper.company.*;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrderServiceImpl extends BaseServiceImpl implements WorkOrderService {

    @Resource
    private WorkOrderModelMapper workOrderMapper;

    @Resource
    private WorkOrderLockXrefModelMapper workOrderLockXrefModelMapper;

    @Resource
    private WorkOrderTransportXrefModelMapper workOrderTransportXrefModelMapper;

    @Resource
    private WorkOrderAddressModelMapper workOrderAddressModelMapper;

    @Resource
    private AttachmentModelMapper attachmentModelMapper;

    @Override
    public int deleteByPrimaryKey(Long workOrderId) {
        return workOrderMapper.deleteByPrimaryKey(workOrderId);
    }

    @Override
    public int insert(WorkOrderModel record) {
        return workOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkOrderModel record) {
        return workOrderMapper.insertSelective(record);
    }

    @Override
    public WorkOrderModel selectByPrimaryKey(Long workOrderId) {
        return workOrderMapper.selectByPrimaryKey(workOrderId);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkOrderModel record) {
        return workOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkOrderModel record) {
        return workOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkOrderModel> getWorkOrdersByCondition(WorkOrderSearchModel condition) {
        condition.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
        return workOrderMapper.getWorkOrdersByCondition(condition);
    }

    /**
     * 创建工单
     *
     * @param workOrderModel
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public WorkOrderModel createWorkOrder(WorkOrderModel workOrderModel) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        workOrderModel.setCompanyId(companyId);
        workOrderModel.setCreateDate(currentTimeStamp);
        workOrderModel.setStatusCode(20001L);
        workOrderModel.setLastUpdateDate(currentTimeStamp);
        workOrderModel.setLastUpdateUser(currentUser);
        this.workOrderMapper.insert(workOrderModel);
        // add relationship
        this.addXrefWithWorkOrder(workOrderModel);

        return workOrderModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public WorkOrderModel updateWorkOrder(WorkOrderModel workOrderModel) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();

        workOrderModel.setLastUpdateDate(currentTimeStamp);
        workOrderModel.setLastUpdateUser(currentUser);
        this.workOrderMapper.updateByPrimaryKey(workOrderModel);

        List<Long> workOrderIdList = new ArrayList<>();
        workOrderIdList.add(workOrderModel.getWorkOrderId());

        // 删除既有关系
        workOrderLockXrefModelMapper.deleteByWorkOrderId(workOrderIdList);
        workOrderTransportXrefModelMapper.deleteByWorkOrderId(workOrderIdList);
        workOrderAddressModelMapper.deleteByWorkOrderId(workOrderIdList);
        // 添加新的关系
        this.addXrefWithWorkOrder(workOrderModel);

        return workOrderModel;
    }

    private WorkOrderModel addXrefWithWorkOrder (WorkOrderModel workOrderModel){
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();

        // 添加与工单相关联的电子封锁
        List<WorkOrderLockXrefModel> workOrderLockXrefModels = new ArrayList<>();
        workOrderModel.getLockList().forEach(lockModel -> {
            WorkOrderLockXrefModel workOrderLockXrefModel = new WorkOrderLockXrefModel();
            workOrderLockXrefModel.setBarCode(lockModel.getBarCode());
            workOrderLockXrefModel.setWorkOrderId(workOrderModel.getWorkOrderId());
            workOrderLockXrefModel.setIsActive(true);
            workOrderLockXrefModel.setCreateDate(currentTimeStamp);
            workOrderLockXrefModel.setLastUpdateDate(currentTimeStamp);
            workOrderLockXrefModel.setLastUpdateUser(currentUser);

            workOrderLockXrefModels.add(workOrderLockXrefModel);
        });
        this.workOrderLockXrefModelMapper.addInbatch(workOrderLockXrefModels);

        // 添加与运单相关的运输载体
        List<WorkOrderTransportXrefModel> workOrderTransportXrefModels = new ArrayList<>();
        workOrderModel.getTransportList().forEach( transportModel -> {
            WorkOrderTransportXrefModel workOrderTransportXrefModel = new WorkOrderTransportXrefModel();
            workOrderTransportXrefModel.setIsActive(true);
            workOrderTransportXrefModel.setTranId(transportModel.getId());
            workOrderTransportXrefModel.setWorkOrderId(workOrderModel.getWorkOrderId());
            workOrderTransportXrefModel.setCreateDate(currentTimeStamp);
            workOrderTransportXrefModel.setLastUpdateDate(currentTimeStamp);
            workOrderTransportXrefModel.setLastUpdateUser(currentUser);

            workOrderTransportXrefModels.add(workOrderTransportXrefModel);
        });
        this.workOrderTransportXrefModelMapper.batchInsert(workOrderTransportXrefModels);

        // 添加与订单相关的位置信息
        List<WorkOrderAddressModel> workOrderAddressModels = new ArrayList<>();
        workOrderModel.getAddressList().forEach(workOrderAddressModel -> {
            workOrderAddressModel.setWorkOrderId(workOrderModel.getWorkOrderId());
            workOrderAddressModel.setCreateDate(currentTimeStamp);
            workOrderAddressModel.setLastUpdateDate(currentTimeStamp);
            workOrderAddressModel.setLastUpdateUser(currentUser);
        });
        this.workOrderAddressModelMapper.batchInsert(workOrderModel.getAddressList());

        return workOrderModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int removeWorkOrders(List<Long> workOrderIdList) {

        workOrderLockXrefModelMapper.deleteByWorkOrderId(workOrderIdList);
        workOrderTransportXrefModelMapper.deleteByWorkOrderId(workOrderIdList);
        workOrderAddressModelMapper.deleteByWorkOrderId(workOrderIdList);
        int count = workOrderMapper.deleteByWorkOrderId(workOrderIdList);
        return count;
    }

    @Override
    public WorkOrderModel getWorkOrderByRFID(String rfid) {
        return workOrderMapper.selectWorkOrderByRFID(rfid);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public WorkOrderModel getWorkOrderByWayBillNumber(String wayBillNumber) {
        return workOrderMapper.getWorkOrderByWayBillNumber(wayBillNumber);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public AttachmentModel uploadWorkOrderAttachment(Long attachmentType, MultipartFile file) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        AttachmentModel attachmentModel = new AttachmentModel();

        // 获得File的base64编码
        String attachmentBase64 = ImageConvertIntoBase64Utils.encodeImageToBase64(file);

        if (attachmentType == null){
            attachmentModel.setAttachmentType(60001L);
        } else {
            attachmentModel.setAttachmentType(attachmentType);
        }
        attachmentModel.setAttachmentName(file.getOriginalFilename());
        attachmentModel.setAttachmentCode(attachmentBase64);
        attachmentModel.setCreateDate(currentTimeStamp);
        attachmentModel.setLastUpdateUser(currentUser);
        attachmentModel.setLastUpdateDate(currentTimeStamp);

        this.attachmentModelMapper.insert(attachmentModel);

        return attachmentModel;
    }

}

