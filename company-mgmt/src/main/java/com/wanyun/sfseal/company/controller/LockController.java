/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.ImportResultModel;
import com.wanyun.sfseal.company.service.*;
import com.wanyun.sfseal.db.common.model.PageableModel;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * The rest controller exposing API for retrieving and maintaining Lock.
 *
 * @author WanYun
 */
@RestController
@RequestMapping("/lock")
public class LockController {

    private final LockModelService lockModelService;

    private final DispenseRecordModelService dispenseRecordModelService;

    private final CompanyLockXrefModelService companyLockXrefModelService;

    private final ImportRecordModelService importRecordModelService;

    private final LockImportXrefModelService lockImportXrefModelService;

    public LockController(LockModelService lockModelService, DispenseRecordModelService dispenseRecordModelService, CompanyLockXrefModelService companyLockXrefModelService, ImportRecordModelService importRecordModelService, LockImportXrefModelService lockImportXrefModelService) {
        this.lockModelService = lockModelService;
        this.dispenseRecordModelService = dispenseRecordModelService;
        this.companyLockXrefModelService = companyLockXrefModelService;
        this.importRecordModelService = importRecordModelService;
        this.lockImportXrefModelService = lockImportXrefModelService;
    }

    /**
     * Get locks by condition
     * @param lockSearhModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/query")
    public ResponseEntity<PageInfo<LockModel>> getLocksByConditon(@RequestBody PageableModel<LockSearchModel> lockSearhModel) throws WanYunBusinessException {
        // Construct page helper parameter
        PageHelper.startPage(lockSearhModel.getPageNum(),
                lockSearhModel.getPageSize(),
                lockSearhModel.isCount(),
                lockSearhModel.getReasonable(),
                lockSearhModel.getPageSizeZero());

        List<LockModel> lockSerachResult = lockModelService.getLocksByCondition(lockSearhModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(lockSerachResult));
    }

    /**
     * Select unused lock for work order
     * @param searchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/workorder")
    public ResponseEntity<List<LockModel>> getLocksForWorkOrder(@RequestBody LockSearchModel searchModel) throws WanYunBusinessException {
        return ResponseEntity.ok(lockModelService.getLocksForWorkOrder(searchModel));
    }
    /**
     * 批量导入电子封锁
     * @param companyId
     * @param file
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/imports")
    public ResponseEntity<ImportResultModel> importLocks(@RequestParam(value = "companyId", required = false) Long companyId, @RequestParam(value = "file", required = false) MultipartFile file) throws WanYunBusinessException{
        return ResponseEntity.ok(lockModelService.importLocks(companyId,file));
    }

    /**
     * 更新电子封锁
     * @param lockModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("")
    public ResponseEntity<LockModel> updateLock(@RequestBody @Valid LockModel lockModel) throws WanYunBusinessException {
        return ResponseEntity.ok(lockModelService.updateLock(lockModel));
    }

    /**
     * 分发电子封锁
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/dispense/{companyId}")
    public ResponseEntity<Integer> dispenseLocks(@PathVariable(value = "companyId") Long companyId,
                                                  @RequestBody List<String> lockRfidList) throws WanYunBusinessException {
        return ResponseEntity.ok(lockModelService.dispenseLock(companyId,lockRfidList));
    }

    /**
     * 删除电子封锁
     * @param lockRfidList
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/remove")
    public ResponseEntity<Integer> removeLocks( @RequestBody List<String> lockRfidList) throws WanYunBusinessException {
        return ResponseEntity.ok(lockModelService.removeLocks(lockRfidList));
    }

    @PostMapping("/dispense/query")
    public ResponseEntity<PageInfo<DispenseRecordModel>> getDispenseRecordByConditon(@RequestBody PageableModel<DispenseRecordSearchModel> dispenseRecordSearhModel) throws WanYunBusinessException {
        // Construct page helper parameter
        PageHelper.startPage(dispenseRecordSearhModel.getPageNum(),
                dispenseRecordSearhModel.getPageSize(),
                dispenseRecordSearhModel.isCount(),
                dispenseRecordSearhModel.getReasonable(),
                dispenseRecordSearhModel.getPageSizeZero());

        List<DispenseRecordModel> dispenseSerachResult = dispenseRecordModelService.getDispenseByCondition(dispenseRecordSearhModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(dispenseSerachResult));
    }

    /**
     * 通过分发记录获取所有的RFID
     * @return
     */
    @GetMapping("/dispense")
    public ResponseEntity<List<String>> getAllRfidByDispenseRecordId(@RequestParam("dispenseRecordId") Long dispenseRecordId) throws WanYunBusinessException {

        return ResponseEntity.ok(companyLockXrefModelService.getAllRfidsByDispenseRecordId(dispenseRecordId));
    }

    /**
     * 撤回已经分发的锁
     * @return
     */
    @GetMapping("/rollback")
    public ResponseEntity<Integer> rollbackDispenseLocks(@RequestParam("dispenseRecordId") Long dispenseRecordId) throws WanYunBusinessException {

        return ResponseEntity.ok(dispenseRecordModelService.rollbackDispenseLocks(dispenseRecordId));
    }

    /**
     * 重新分发
     * @param dispenseRecord
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/redispense")
    public ResponseEntity<Integer> redispense(@RequestBody DispenseRecordModel dispenseRecord) throws WanYunBusinessException {
        return ResponseEntity.ok(dispenseRecordModelService.redispense(dispenseRecord));
    }

    /**
     * 接收分发
     * @param dispenseRecord
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/receive")
    public ResponseEntity<Integer> receive(@RequestBody DispenseRecordModel dispenseRecord) throws WanYunBusinessException {
        return ResponseEntity.ok(dispenseRecordModelService.receive(dispenseRecord));
    }

    /**
     * 购买
     * @param purchCount
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/purch")
    public ResponseEntity<Integer> purchLocks(@RequestParam("purchCount") int purchCount) throws WanYunBusinessException {
        return ResponseEntity.ok(lockModelService.purchLocks(purchCount));
    }

    /**
     * 查询导入记录
     * @param importRecordSearhModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/imports/query")
    public ResponseEntity<PageInfo<ImportRecordModel>> getImportRecordByConditon(@RequestBody PageableModel<ImportRecordSearchModel> importRecordSearhModel) throws WanYunBusinessException {
        // Construct page helper parameter
        PageHelper.startPage(importRecordSearhModel.getPageNum(),
                importRecordSearhModel.getPageSize(),
                importRecordSearhModel.isCount(),
                importRecordSearhModel.getReasonable(),
                importRecordSearhModel.getPageSizeZero());

        List<ImportRecordModel> importSerachResult = importRecordModelService.getImportRecordByCondition(importRecordSearhModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(importSerachResult));
    }

    /**
     *
     * @param importId
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/imports")
    public ResponseEntity<List<String>> getRfidsByImportId(@RequestParam("importId") Long importId) throws WanYunBusinessException {
        return ResponseEntity.ok(lockImportXrefModelService.getRfidsByImportId(importId));
    }

    @GetMapping("/sealinfo/{barcode}")
    public ResponseEntity<LockModel> getLockSealInfoByBarCode(@PathVariable("barcode") String barcode) {
        LockModel lockSealInfo = lockModelService.getLockSealInfoByBarCode(barcode);
        return ResponseEntity.ok(lockSealInfo);
    }
}
