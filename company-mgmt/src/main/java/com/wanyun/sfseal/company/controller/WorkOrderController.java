/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.company.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.ImportResultModel;
import com.wanyun.sfseal.company.service.TransportModelService;
import com.wanyun.sfseal.company.service.WorkOrderService;
import com.wanyun.sfseal.db.common.model.PageableModel;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * The rest controller exposing API for retrieving and maintaining work order.
 *
 * @author WanYun
 */
@RestController
@RequestMapping("/workorder")
public class WorkOrderController {
    private final WorkOrderService workOrderService;

    private final TransportModelService transportModelService;

    public WorkOrderController(WorkOrderService workOrderService, TransportModelService transportModelService) {
        this.workOrderService = workOrderService;
        this.transportModelService = transportModelService;
    }

    /**
     * Get work orders by condition
     * @param workOrderSearhModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/query")
    public ResponseEntity<PageInfo<WorkOrderModel>> getWorkOrdersByConditon(@RequestBody PageableModel<WorkOrderSearchModel> workOrderSearhModel) throws WanYunBusinessException {
        // Construct page helper parameter
        PageHelper.startPage(workOrderSearhModel.getPageNum(),
                workOrderSearhModel.getPageSize(),
                workOrderSearhModel.isCount(),
                workOrderSearhModel.getReasonable(),
                workOrderSearhModel.getPageSizeZero());

        List<WorkOrderModel> workOrderModelList = workOrderService.getWorkOrdersByCondition(workOrderSearhModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(workOrderModelList));
    }

    /**
     * 创建电子工单
     * @param workOrderModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("")
    public ResponseEntity<WorkOrderModel> createWorkOrder(@RequestBody @Valid WorkOrderModel workOrderModel ) throws WanYunBusinessException {
        return  ResponseEntity.ok(workOrderService.createWorkOrder(workOrderModel));
    }

    /**
     * 上传工单附件
     * @param attachmentType
     * @param file
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/attachment")
    public ResponseEntity<AttachmentModel> uploadWorkOrderAttachment(@RequestParam(value = "attachmentType", required = false) Long attachmentType, @RequestParam(value = "file", required = false) MultipartFile file) throws WanYunBusinessException{
        return ResponseEntity.ok(workOrderService.uploadWorkOrderAttachment(attachmentType,file));
    }


    /**
     * 更新电子工单
     * @param workOrderModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("")
    public ResponseEntity<WorkOrderModel> updateWorkOrder(@RequestBody @Valid WorkOrderModel workOrderModel ) throws WanYunBusinessException {
        return  ResponseEntity.ok(workOrderService.updateWorkOrder(workOrderModel));
    }

    /**
     * 删除电子工单
     * @param workOrderIdList
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/remove")
    public ResponseEntity<Integer> removeWorkOrders( @RequestBody List<Long> workOrderIdList) throws WanYunBusinessException {
        return ResponseEntity.ok(workOrderService.removeWorkOrders(workOrderIdList));
    }

    @PostMapping("/transport")
    public ResponseEntity<TransportModel> createTransport(@RequestBody String transportId) throws WanYunBusinessException {
        return  ResponseEntity.ok(transportModelService.createTransport(transportId));
    }

    /**
     * 根据电子封锁RFID查找工单
     * @param rfid
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/{rfid}")
    public ResponseEntity<WorkOrderModel> getWorkOrderByRFID(@PathVariable(value = "rfid") String rfid) throws WanYunBusinessException {
        return ResponseEntity.ok(workOrderService.getWorkOrderByRFID(rfid));
    }
}
