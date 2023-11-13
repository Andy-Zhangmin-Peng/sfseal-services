/*
 * Copyright (c) 2020. 2020. WanYun Corporation. All Rights Reserved.
 *
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.company.ExceptionalSituationModel;
import com.wanyun.sfseal.db.model.company.SealModel;

public interface SealService {

    SealModel seal(SealModel sealModel);

    SealModel sealOff(SealModel sealModel) throws WanYunBusinessException;

    ExceptionalSituationModel reportExceptionalSituation(ExceptionalSituationModel exceptionalSituationModel) throws WanYunBusinessException;
}
