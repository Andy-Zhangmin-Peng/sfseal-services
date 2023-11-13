/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.common.config.CommonConfigProperties;
import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.commonutils.common.util.ReadExcelUtil;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.commonutils.model.ImportConfigureModel;
import com.wanyun.sfseal.commonutils.model.ImportResultModel;
import com.wanyun.sfseal.company.common.constant.CompanyConstants;
import com.wanyun.sfseal.db.mapper.company.*;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

/**
 * This service is the implementation of LockModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class LockModelServiceImpl extends BaseServiceImpl implements LockModelService {

    @Resource
    private LockModelMapper lockModelMapper;

    @Resource
    private CompanyLockXrefModelMapper companyLockXrefModelMapper;

    @Resource
    private DispenseRecordModelMapper dispenseRecordModelMapper;

    @Resource
    private ImportRecordModelMapper importRecordModelMapper;

    @Resource
    private LockImportXrefModelMapper lockImportXrefModelMapper;

    @Autowired
    private CommonConfigProperties commonConfigProperties;

    @Override
    public int deleteByPrimaryKey(String rfid) {
        return lockModelMapper.deleteByPrimaryKey(rfid);
    }

    @Override
    public int insert(LockModel record) {
        return lockModelMapper.insert(record);
    }

    @Override
    public int insertSelective(LockModel record) {
        return lockModelMapper.insertSelective(record);
    }

    @Override
    public LockModel selectByPrimaryKey(String rfid) {
        return lockModelMapper.selectByPrimaryKey(rfid);
    }

    @Override
    public int updateByPrimaryKeySelective(LockModel record) {
        return lockModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LockModel record) {
        return lockModelMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据条件查找锁信息
     *
     * @param condition
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<LockModel> getLocksByCondition(LockSearchModel condition) {
        Long companyId = this .getCurrentLoginInfo().getCompanyId();
        if (companyId != 1){
            condition.setCompanyId(companyId);
        }
        return lockModelMapper.getLocksByCondition(condition);
    }

    /**
     * 批量导入锁信息
     *
     * @param companyId
     * @param file
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public ImportResultModel importLocks(Long companyId, MultipartFile file) throws WanYunBusinessException {
        List<CompanyLockXrefModel> companyLockXrefModelList = new ArrayList<>();
        List<LockImportXrefModel> lockImportXrefModelList = new ArrayList<>();
        ImportResultModel resultModel = new ImportResultModel();
        int importCount = 0;

        // 去除从文件导入的空记录
        List<LockModel> locksRecordFromFile = this.prepareLockFromExcel(file);
        List<LockModel> locks = new ArrayList<>();
        if (!CommonUtil.isListEmpty(locksRecordFromFile))
        locksRecordFromFile.forEach(lockModel -> {
            if (lockModel != null && lockModel.getRfid() != null){
                locks.add(lockModel);
            }
        });
        // 记录从文件读取到非空记录总量
        resultModel.setImportedRecordCount(locks.size());

        if (!CommonUtil.isListEmpty(locks)){
            Set<LockModel> set = new TreeSet<>(Comparator.comparing(LockModel::getRfid));
            set.addAll(locks);
            // 去除重复数据以后的lock list
            List<LockModel> locksWithoutDuplicate = new ArrayList<>(set);
            // 记录表格重复数据的数量
            resultModel.setDuplicatedRecordCount(resultModel.getImportedRecordCount() - locksWithoutDuplicate.size());

            // 检查数据库中已经存在的
            List<LockModel> importLockList = new ArrayList<>();
            if (!CommonUtil.isListEmpty(locksWithoutDuplicate)){
                List<String> rfidList = new ArrayList<>();
                locksWithoutDuplicate.forEach(lockModel -> rfidList.add(lockModel.getRfid()));
                List<LockModel> existingLocks = lockModelMapper.getLocksByRfids(rfidList);
                Set<String> existingRfidSet = new HashSet<>();
                existingLocks.forEach(existingLock -> existingRfidSet.add(existingLock.getRfid()));

                if (!CommonUtil.isListEmpty(existingLocks)){
                    locksWithoutDuplicate.forEach(lockModel -> {
                        if (!existingRfidSet.contains(lockModel.getRfid())){
                            importLockList.add(lockModel);
                        }
                    });
                }else {
                    importLockList.addAll(locksWithoutDuplicate);
                }
            }

            // 记录数据库中以存在数据的数量
            resultModel.setExistedRecordCount(resultModel.getImportedRecordCount() - resultModel.getDuplicatedRecordCount() - importLockList.size());

            Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());
            String currentUser = this.getCurrentLoginInfo().getAccountName();

            if (!CommonUtil.isListEmpty(importLockList)){
                if (companyId == null) {
                    importLockList.forEach(lockModel -> {
                        lockModel.setStatusCode(10001L);
                        lockModel.setCreateDate(currentTimestap);
                        lockModel.setLastUpdateDate(currentTimestap);
                        lockModel.setLastUpdateUser(currentUser);
                    });
                } else {
                    // 添加分发记录
                    DispenseRecordModel dispenseRecord = this.addDispenseRecord(companyId, importLockList.size());

                    importLockList.forEach(lockModel -> {
                        CompanyLockXrefModel companyLockXref = new CompanyLockXrefModel();
                        companyLockXref.setCompanyId(companyId);
                        companyLockXref.setRfid(lockModel.getRfid());
                        companyLockXref.setIsActive(true);
                        companyLockXref.setDispenseRecordId(dispenseRecord.getDispenseRecordId());
                        companyLockXref.setCreateDate(currentTimestap);
                        companyLockXref.setLastUpdateDate(currentTimestap);
                        companyLockXref.setLastUpdateUser(currentUser);
                        companyLockXrefModelList.add(companyLockXref);

                        lockModel.setStatusCode(10002L);
                        lockModel.setCreateDate(currentTimestap);
                        lockModel.setLastUpdateDate(currentTimestap);
                        lockModel.setLastUpdateUser(currentUser);
                    });
                    if (companyLockXrefModelList.size() > 0) {
                        companyLockXrefModelMapper.addInBatch(companyLockXrefModelList);
                    }
                }

                importCount = lockModelMapper.addInBatch(importLockList);

                ImportRecordModel importRecord = new ImportRecordModel();
                importRecord.setImportCount(Long.valueOf(importCount));
                importRecord.setCreateDate(currentTimestap);
                importRecord.setLastUpdateDate(currentTimestap);
                importRecord.setLastUpdateUser(currentUser);
                importRecordModelMapper.insert(importRecord);

                importLockList.forEach(lockModel -> {
                    LockImportXrefModel lockImportXrefModel = new LockImportXrefModel();
                    lockImportXrefModel.setImportId(importRecord.getImportId());
                    lockImportXrefModel.setRfid(lockModel.getRfid());
                    lockImportXrefModel.setLastUpdateDate(currentTimestap);
                    lockImportXrefModel.setCreateDate(currentTimestap);
                    lockImportXrefModel.setLastUpdateUser(currentUser);
                    lockImportXrefModelList.add(lockImportXrefModel);
                });
                if (!CommonUtil.isListEmpty(lockImportXrefModelList)) {
                    lockImportXrefModelMapper.batchInsert(lockImportXrefModelList);
                }
            }
        }
        resultModel.setInsertedRecordCount(importCount);
        return resultModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public LockModel updateLock(LockModel lockModel) throws WanYunBusinessException {
        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());
        String currentUser = this.getCurrentLoginInfo().getAccountName();

        lockModel.setLastUpdateUser(currentUser);
        lockModel.setLastUpdateDate(currentTimestap);
        lockModel.setStatusCode(10002L);
        lockModelMapper.updateByPrimaryKey(lockModel);

        DispenseRecordModel dispenseRecord = this.addDispenseRecord(lockModel.getCompanyId(), 1);

        CompanyLockXrefModel companyLockXref = new CompanyLockXrefModel();
        companyLockXref.setCompanyId(lockModel.getCompanyId());
        companyLockXref.setRfid(lockModel.getRfid());
        companyLockXref.setIsActive(true);
        companyLockXref.setDispenseRecordId(dispenseRecord.getDispenseRecordId());
        companyLockXref.setCreateDate(currentTimestap);
        companyLockXref.setLastUpdateDate(currentTimestap);
        companyLockXref.setLastUpdateUser(currentUser);

        companyLockXrefModelMapper.insert(companyLockXref);
        return lockModel;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int dispenseLock(Long companyId, List<String> lockRfidList) throws WanYunBusinessException {

        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());
        String createUser = this.getCurrentLoginInfo().getAccountName();

        // 批量修改锁的状态
        int count = lockModelMapper.updateLockStatus(lockRfidList, 10002L, createUser);

        // 添加分发记录
        DispenseRecordModel dispenseRecord = this.addDispenseRecord(companyId, count);

        // 批量创建锁和公司关系
        companyLockXrefModelMapper.dispenseLock(companyId, lockRfidList, createUser, dispenseRecord.getDispenseRecordId());

        return count;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int removeLocks(List<String> lockRfidList) {

        return lockModelMapper.deleteLocks(lockRfidList);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int purchLocks(int purchCount) {
        List<String> rfidList = lockModelMapper.getPurchLockRfids(purchCount);
        List<CompanyLockXrefModel> companyLockXrefModelList = new ArrayList<>();

        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());
        Long companyId = this.getCurrentLoginInfo().getCompanyId();

        int count = 0;
        if (!CommonUtil.isListEmpty(rfidList)){
            DispenseRecordModel dispenseRecord = this.addDispenseRecord(companyId, rfidList.size());
            rfidList.forEach(rfid -> {
                CompanyLockXrefModel companyLockXref = new CompanyLockXrefModel();
                companyLockXref.setCompanyId(companyId);
                companyLockXref.setRfid(rfid);
                companyLockXref.setIsActive(true);
                companyLockXref.setDispenseRecordId(dispenseRecord.getDispenseRecordId());
                companyLockXref.setCreateDate(currentTimestap);
                companyLockXref.setLastUpdateDate(currentTimestap);
                companyLockXref.setLastUpdateUser("SystemAdmin");
                companyLockXrefModelList.add(companyLockXref);
            });

            if (companyLockXrefModelList.size() > 0) {
                companyLockXrefModelMapper.addInBatch(companyLockXrefModelList);
            }
            // 批量修改锁的状态
            count = lockModelMapper.updateLockStatus(rfidList, 10002L, "SystemAdmin");
        }

        return count;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<LockModel> getLocksForWorkOrder(LockSearchModel searchModel) {
        searchModel.setCompanyId(this.getCurrentLoginInfo().getCompanyId());
        searchModel.setStatusCode(10003L);

        return lockModelMapper.getLocksForWorkOrder(searchModel);
    }

    @Override
    public LockModel getLockSealInfoByBarCode(String barcode) {

        return lockModelMapper.getLockSealInfoByBarCode(barcode);
    }

    /**
     * 添加分发记录
     *
     * @param companyId
     * @param count
     * @return
     */
    private DispenseRecordModel addDispenseRecord(Long companyId, int count) {

        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());
        String createUser = this.getCurrentLoginInfo().getAccountName();

        DispenseRecordModel dispenseRecord = new DispenseRecordModel();
        dispenseRecord.setCompanyId(companyId);
        dispenseRecord.setDispenseCount(Long.valueOf(count));
        dispenseRecord.setIsActive(true);
        dispenseRecord.setAccpeted(false);
        dispenseRecord.setCreateDate(currentTimestap);
        dispenseRecord.setLastUpdateDate(currentTimestap);
        dispenseRecord.setLastUpdateUser(createUser);
        dispenseRecordModelMapper.insert(dispenseRecord);

        return dispenseRecord;
    }

    private List<LockModel> prepareLockFromExcel(MultipartFile file) throws WanYunBusinessException {
        List<ImportConfigureModel> configureList = null;
        Map<String, List<ImportConfigureModel>> importConfigureMap = commonConfigProperties.getImportConfigure();
        if (!CommonUtil.isMapEmpty(importConfigureMap) && importConfigureMap.containsKey(CompanyConstants.EXPORT_CONFIGURE)) {
            configureList = importConfigureMap.get(CompanyConstants.EXPORT_CONFIGURE);
        }
        return ReadExcelUtil.readFile(file, LockModel.class, CompanyConstants.EXPORT_CONFIGURE, configureList);
    }

}




