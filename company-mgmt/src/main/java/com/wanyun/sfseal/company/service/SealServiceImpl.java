/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.mapper.company.*;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SealServiceImpl extends BaseServiceImpl implements SealService{


    private final WorkOrderService workOrderService;

    private final LockModelService lockModelService;

    private final LockAttachmentXrefModelService lockAttachmentXrefModelService;

    private final TransportModelMapper transportModelMapper;

    private final WorkOrderLockXrefModelMapper workOrderLockXrefModelMapper;

    private final WorkOrderTransportXrefModelMapper workOrderTransportXrefModelMapper;

    private final ExceptionalSituationModelMapper exceptionalSituationModelMapper;

    private final WorkOrderAddressModelMapper workOrderAddressModelMapper;

    private final ExceptionalSituationAttachmentXrefMapper exceptionalSituationAttachmentXrefMapper;

    public SealServiceImpl(WorkOrderService workOrderService,
                           LockModelService lockModelService,
                           WorkOrderLockXrefModelMapper workOrderLockXrefModelMapper,
                           WorkOrderTransportXrefModelMapper workOrderTransportXrefModelMapper,
                           ExceptionalSituationModelMapper exceptionalSituationModelMapper,
                           WorkOrderAddressModelMapper workOrderAddressModelMapper,
                           ExceptionalSituationAttachmentXrefMapper exceptionalSituationAttachmentXrefMapper,
                           TransportModelMapper transportModelMapper,
                           LockAttachmentXrefModelService lockAttachmentXrefModelService) {
        this.workOrderService = workOrderService;
        this.lockModelService = lockModelService;
        this.workOrderLockXrefModelMapper = workOrderLockXrefModelMapper;
        this.workOrderTransportXrefModelMapper = workOrderTransportXrefModelMapper;
        this.exceptionalSituationModelMapper = exceptionalSituationModelMapper;
        this.workOrderAddressModelMapper = workOrderAddressModelMapper;
        this.exceptionalSituationAttachmentXrefMapper = exceptionalSituationAttachmentXrefMapper;
        this.transportModelMapper = transportModelMapper;
        this.lockAttachmentXrefModelService = lockAttachmentXrefModelService;
    }

    /**
     * 施封过程
     * @param sealModel
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public SealModel seal(SealModel sealModel) {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        WorkOrderModel workOrderModel = sealModel.getWorkOrder();
        LockModel lockModel = sealModel.getLock();

        // 根据lockmodel中的车船号，查看是否已经存在
        TransportModel transportModel = transportModelMapper.getTransportByTranId(lockModel.getTransprortId(), companyId);
        // 如果不存在则生成新的
        if (transportModel == null || transportModel.getId() == null) {
            transportModel = new TransportModel();
            transportModel.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
            transportModel.setTransprortId(lockModel.getTransprortId());
            transportModel.setCreateDate(currentTimeStamp);
            transportModel.setLastUpdateDate(currentTimeStamp);
            transportModel.setLastUpdateUser(currentUser);
            transportModelMapper.insert(transportModel);
        }

        //更改锁状态为使用中
        lockModel.setStatusCode(10004L);
        lockModel.setTranId(transportModel.getId());
        lockModel.setLockUserId(this.getCurrentLoginInfo().getAccountId());
        lockModel.setLastUpdateDate(currentTimeStamp);
        lockModel.setLastUpdateUser(currentUser);
        lockModelService.updateByPrimaryKey(lockModel);

        // 给电子封锁绑定施封附件
        if (!CommonUtil.isListEmpty(lockModel.getAttachmentModelList())){
            List<LockAttachmentXrefModel> lockAttachmentXrefModelList = lockModel.getAttachmentModelList().stream().map(attachmentModel -> {
                LockAttachmentXrefModel lockAttachmentXrefModel =  new LockAttachmentXrefModel();
                lockAttachmentXrefModel.setRfid(lockModel.getRfid());
                lockAttachmentXrefModel.setAttachmentId(attachmentModel.getAttachmentId());
                lockAttachmentXrefModel.setCreateDate(currentTimeStamp);
                lockAttachmentXrefModel.setLastUpdateDate(currentTimeStamp);
                lockAttachmentXrefModel.setLastUpdateUser(currentUser);
                return lockAttachmentXrefModel;
            }).collect(Collectors.toList());
            lockAttachmentXrefModelService.batchInsert(lockAttachmentXrefModelList);
        }

        //如果工单ID为空，说明为非计划工单
        if ( workOrderModel.getWorkOrderId() == null ){

            // 如果运单ID不为空，则根据运单号查找工单
            if (workOrderModel.getWayBillNumber() != null) {
                // 根据运单号查找是否存在工单
                WorkOrderModel tempworkOrderModel = workOrderService.getWorkOrderByWayBillNumber(workOrderModel.getWayBillNumber());

                // 如果已经存在则无需再创建工单，只需要加入对应锁和地址信息
                if (tempworkOrderModel != null){
                    // 工单已经存在
                    // 添加工单和锁的关联关系
                    WorkOrderLockXrefModel workOrderLockXrefModel = new WorkOrderLockXrefModel();
                    workOrderLockXrefModel.setWorkOrderId(tempworkOrderModel.getWorkOrderId());
                    workOrderLockXrefModel.setBarCode(lockModel.getBarCode());
                    workOrderLockXrefModel.setIsActive(true);
                    workOrderLockXrefModel.setCreateDate(currentTimeStamp);
                    workOrderLockXrefModel.setLastUpdateDate(currentTimeStamp);
                    workOrderLockXrefModel.setLastUpdateUser(currentUser);
                    workOrderLockXrefModelMapper.insert(workOrderLockXrefModel);

                    WorkOrderTransportXrefModel workOrderTransportXrefModel = new WorkOrderTransportXrefModel();
                    workOrderTransportXrefModel.setWorkOrderId(tempworkOrderModel.getWorkOrderId());
                    workOrderTransportXrefModel.setTranId(lockModel.getTranId());
                    workOrderTransportXrefModel.setIsActive(true);
                    workOrderTransportXrefModel.setLastUpdateDate(currentTimeStamp);
                    workOrderTransportXrefModel.setCreateDate(currentTimeStamp);
                    workOrderTransportXrefModel.setLastUpdateUser(currentUser);
                    workOrderTransportXrefModelMapper.insert(workOrderTransportXrefModel);
                } else {
                    // 根据运单号未找到对应工单，则创建工单

                    // Android端，如果没有读取到工单信息，则将施封时定位的位置放入workorder 的 AddressList里面并给type为30003（实际开始位置）
                    // 将当前运输载体id仍然只需要放在lockmodel的Tran_id 里面
                    // 将当前电子封锁的序列号（bar_code）仍然只需要放在lockmodel的bar code 里面
                    workOrderModel.getLockList().add(lockModel);
                    workOrderModel.getTransportList().add(transportModel);
                    workOrderModel.setCompanyId(companyId);
                    workOrderModel = workOrderService.createWorkOrder(workOrderModel);
                }
            } else {
                // 工单号为空，运单号也为空，则需创建新的工单
                workOrderModel.getLockList().add(lockModel);
                workOrderModel.getTransportList().add(transportModel);
                workOrderModel.setCompanyId(companyId);
                workOrderModel = workOrderService.createWorkOrder(workOrderModel);
            }
        }
        // 更改工单状态为 运输中
        workOrderModel.setStatusCode(20002L);
        workOrderModel.setStartTime(currentTimeStamp);
        workOrderModel.setLastUpdateUser(currentUser);
        workOrderModel.setLastUpdateDate(currentTimeStamp);
        workOrderService.updateByPrimaryKey(workOrderModel);

        //添加施封位置信息
        if(!CommonUtil.isListEmpty(workOrderModel.getAddressList())) {
            for (WorkOrderAddressModel addressModel: workOrderModel.getAddressList()) {
                if (addressModel.getAddressType() == 30003L){
                    WorkOrderAddressModel existingAddress = workOrderAddressModelMapper.getAddressByWorkorderIdAndAddressType(workOrderModel.getWorkOrderId(),30003L);
                    if (existingAddress == null){
                        addressModel.setWorkOrderId(workOrderModel.getWorkOrderId());
                        addressModel.setLastUpdateDate(currentTimeStamp);
                        addressModel.setCreateDate(currentTimeStamp);
                        addressModel.setLastUpdateUser(currentUser);
                        workOrderAddressModelMapper.insert(addressModel);
                    }
                }

            }
        }

        // 非计划工单，workorder 里面车船list为空，不存在此异常
        if (!CommonUtil.isListEmpty(workOrderModel.getTransportList())) {
            boolean transportMatch = false;
            for (TransportModel orgTansportModel : workOrderModel.getTransportList()){
                if (orgTansportModel.getId().equals(lockModel.getTranId())) {
                    transportMatch = true;
                    break;
                }
            }
            if (!transportMatch) {
                //TODO 上报运输载体不一致异常
            }
        }

        // 上报承运人不符异常
        if(workOrderModel.getTempCarriera() != null){
            ExceptionalSituationModel exceptionalSituationModel = new ExceptionalSituationModel();
            exceptionalSituationModel.setExceptionLevel(40002L);
            exceptionalSituationModel.setExceptionTypeCode(50001L);
            exceptionalSituationModel.setExceptionMessage("承运人与实际工单不符");
            exceptionalSituationModel.setCompanyId(companyId);
            exceptionalSituationModel.setBarCode(lockModel.getBarCode());
            exceptionalSituationModel.setWorkOrderId(workOrderModel.getWorkOrderId());
//            exceptionalSituationModelMapper.insert(exceptionalSituationModel);
        }

        //TODO 上报位置不一致异常
        return sealModel;
    }

    /**
     * 拆封过程
     * @param sealModel
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public SealModel sealOff(SealModel sealModel) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        WorkOrderModel workOrderModel = sealModel.getWorkOrder();
        LockModel lockModel = sealModel.getLock();

        // 更改电子封锁状态
        lockModel.setStatusCode(10005L);
        lockModel.setLastUpdateDate(currentTimeStamp);
        lockModel.setLastUpdateUser(currentUser);
        lockModelService.updateByPrimaryKey(lockModel);

        // 添加工单结束实际地址
        WorkOrderAddressModel actualEndAddress = new WorkOrderAddressModel();
        for (WorkOrderAddressModel workOrderAddressModel : workOrderModel.getAddressList()){
            if (workOrderAddressModel.getAddressType() == 30004L){
                WorkOrderAddressModel existingAddress = workOrderAddressModelMapper.getAddressByWorkorderIdAndAddressType(workOrderModel.getWorkOrderId(),30004L);
                if (existingAddress == null){
                    actualEndAddress.setAddressDetail(workOrderAddressModel.getAddressDetail());
                    actualEndAddress.setWorkOrderId(workOrderModel.getWorkOrderId());
                    actualEndAddress.setAddressType(30004L);
                    actualEndAddress.setLngLat(workOrderAddressModel.getLngLat());
                    actualEndAddress.setLastUpdateDate(currentTimeStamp);
                    actualEndAddress.setCreateDate(currentTimeStamp);
                    actualEndAddress.setLastUpdateUser(currentUser);
                    workOrderAddressModelMapper.insert(actualEndAddress);
                }
            }
        }

        // TODO 上报位置不一致异常

        if(workOrderModel.getTempCarriera() != null){
            // TODO 上报承运人不符异常
            ExceptionalSituationModel exceptionalSituationModel = new ExceptionalSituationModel();
            exceptionalSituationModel.setBarCode(lockModel.getBarCode());
            exceptionalSituationModel.setExceptionTypeCode(50001L);
            exceptionalSituationModel.setExceptionLevel(40001L);
            exceptionalSituationModel.setReportAddressId(actualEndAddress.getAddressId());
            exceptionalSituationModel.setWorkOrderId(workOrderModel.getWorkOrderId());
            exceptionalSituationModel.setAddressModel(actualEndAddress);
            exceptionalSituationModel.setCompanyId(companyId);
            exceptionalSituationModel.setExceptionMessage("");
//            this.reportExceptionalSituation(exceptionalSituationModel);
        }

        boolean transportMatch = false;
        for (TransportModel transportModel : workOrderModel.getTransportList()){
            if (transportModel.getId().equals(lockModel.getTranId())) {
                transportMatch = true;
                break;
            }
        }

        if (!transportMatch) {
            //TODO 上报运输载体不一致异常
            ExceptionalSituationModel exceptionalSituationModel = new ExceptionalSituationModel();
            exceptionalSituationModel.setBarCode(lockModel.getBarCode());
            exceptionalSituationModel.setExceptionTypeCode(50001L);
            exceptionalSituationModel.setExceptionLevel(40001L);
            exceptionalSituationModel.setReportAddressId(actualEndAddress.getAddressId());
            exceptionalSituationModel.setWorkOrderId(workOrderModel.getWorkOrderId());
            exceptionalSituationModel.setCompanyId(companyId);
            exceptionalSituationModel.setAddressModel(actualEndAddress);
            exceptionalSituationModel.setExceptionMessage("");
//            this.reportExceptionalSituation(exceptionalSituationModel);
        }

        workOrderModel.setStatusCode(20003L);
        workOrderModel.setEndTime(currentTimeStamp);
        workOrderModel.setStartTime(currentTimeStamp);
        workOrderModel.setLastUpdateUser(currentUser);
        workOrderModel.setLastUpdateDate(currentTimeStamp);
        workOrderService.updateByPrimaryKey(workOrderModel);

        return sealModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public ExceptionalSituationModel reportExceptionalSituation(ExceptionalSituationModel exceptionalSituationModel) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();
        Long accountId =  this.getCurrentLoginInfo().getAccountId();
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        if (exceptionalSituationModel.getAddressModel() == null){
            throw new WanYunBusinessException("异常地址不能为空");
        }

        WorkOrderAddressModel addressModel = exceptionalSituationModel.getAddressModel();
        if (addressModel != null){
            addressModel.setCreateDate(currentTimeStamp);
            addressModel.setLastUpdateUser(currentUser);
            addressModel.setLastUpdateDate(currentTimeStamp);
            workOrderAddressModelMapper.insert(addressModel);
        }

        exceptionalSituationModel.setReportUserId(accountId);
        exceptionalSituationModel.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
        exceptionalSituationModel.setReportAddressId(addressModel.getAddressId());
        exceptionalSituationModel.setLastUpdateDate(currentTimeStamp);
        exceptionalSituationModel.setLastUpdateUser(currentUser);
        exceptionalSituationModel.setCreateDate(currentTimeStamp);
        exceptionalSituationModelMapper.insert(exceptionalSituationModel);

        if (!CommonUtil.isListEmpty(exceptionalSituationModel.getAttachmentIds())){
            exceptionalSituationModel.getAttachmentIds().forEach( attachmentId -> {
                ExceptionalSituationAttachmentXrefModel exceptionalSituationAttachmentXrefModel = new ExceptionalSituationAttachmentXrefModel();
                exceptionalSituationAttachmentXrefModel.setAttachmentId(attachmentId);
                exceptionalSituationAttachmentXrefModel.setExceptionSituationId(exceptionalSituationModel.getExceptionId());
                exceptionalSituationAttachmentXrefModel.setLastUpdateDate(currentTimeStamp);
                exceptionalSituationAttachmentXrefModel.setLastUpdateUser(currentUser);
                exceptionalSituationAttachmentXrefModel.setCreateDate(currentTimeStamp);
                exceptionalSituationAttachmentXrefMapper.insert(exceptionalSituationAttachmentXrefModel);
            });
        }

        return exceptionalSituationModel;
    }
}
