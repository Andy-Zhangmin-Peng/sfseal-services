/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.company.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.company.service.ExceptionalSituationModelService;
import com.wanyun.sfseal.company.service.SealService;
import com.wanyun.sfseal.db.common.model.PageableModel;
import com.wanyun.sfseal.db.model.company.DispenseRecordModel;
import com.wanyun.sfseal.db.model.company.ExceptionalSituationModel;
import com.wanyun.sfseal.db.model.company.ExceptionalSituationSearchModel;
import com.wanyun.sfseal.db.model.company.SealModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seal")
public class SealController {

    private final SealService sealService;

    private final ExceptionalSituationModelService exceptionalSituationModelService;

    public SealController(SealService sealService, ExceptionalSituationModelService exceptionalSituationModelService) {
        this.sealService = sealService;
        this.exceptionalSituationModelService = exceptionalSituationModelService;
    }

    /**
     * 施封
     * @param sealModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("")
    public ResponseEntity<SealModel> seal(@RequestBody SealModel sealModel) throws WanYunBusinessException {
        return ResponseEntity.ok(sealService.seal(sealModel));
    }

    /**
     * 拆封
     * @param sealModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("")
    public ResponseEntity<SealModel> sealOff(@RequestBody SealModel sealModel) throws WanYunBusinessException {
        return ResponseEntity.ok(sealService.sealOff(sealModel));
    }

    /**
     * 上报异常功能
     * @param exceptionalSituationModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/exception")
    public ResponseEntity<ExceptionalSituationModel> reportExceptionalSituation(@RequestBody ExceptionalSituationModel exceptionalSituationModel) throws WanYunBusinessException {
        return ResponseEntity.ok(sealService.reportExceptionalSituation(exceptionalSituationModel));
    }

    /**
     * 查找异常情况
     * @param exceptionalSituationSearchModelPageableModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/query")
    public ResponseEntity<PageInfo<ExceptionalSituationModel>> queryExceptionalSituationByCondition(@RequestBody PageableModel<ExceptionalSituationSearchModel> exceptionalSituationSearchModelPageableModel) throws WanYunBusinessException {
        // Construct page helper parameter
        PageHelper.startPage(exceptionalSituationSearchModelPageableModel.getPageNum(),
                exceptionalSituationSearchModelPageableModel.getPageSize(),
                exceptionalSituationSearchModelPageableModel.isCount(),
                exceptionalSituationSearchModelPageableModel.getReasonable(),
                exceptionalSituationSearchModelPageableModel.getPageSizeZero());

        List<ExceptionalSituationModel> exceptionalSituationSearchResult = exceptionalSituationModelService.getExceptionalSituationByCondition(exceptionalSituationSearchModelPageableModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(exceptionalSituationSearchResult));
    }
}
