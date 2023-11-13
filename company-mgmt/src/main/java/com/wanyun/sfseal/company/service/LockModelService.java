/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.ImportResultModel;
import com.wanyun.sfseal.db.model.company.LockModel;
import com.wanyun.sfseal.db.model.company.LockSearchModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This service is the interface of DepartmentModelServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
public interface LockModelService {


    int deleteByPrimaryKey(String rfid);

    int insert(LockModel record);

    int insertSelective(LockModel record);

    LockModel selectByPrimaryKey(String rfid);

    int updateByPrimaryKeySelective(LockModel record);

    int updateByPrimaryKey(LockModel record);

    List<LockModel> getLocksByCondition(LockSearchModel condition);

    ImportResultModel importLocks(Long companyId, MultipartFile file) throws WanYunBusinessException;

    LockModel updateLock(LockModel lockModel) throws WanYunBusinessException;

    int dispenseLock(Long companyId, List<String> lockRfidList) throws WanYunBusinessException;

    int removeLocks(List<String> lockRfidList);

    int purchLocks(int purchCount);

    List<LockModel> getLocksForWorkOrder(LockSearchModel searchModel);

    LockModel getLockSealInfoByBarCode(String barcode);
}




