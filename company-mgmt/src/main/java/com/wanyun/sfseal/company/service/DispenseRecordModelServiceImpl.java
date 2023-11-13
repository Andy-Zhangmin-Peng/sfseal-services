/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.db.mapper.company.CompanyLockXrefModelMapper;
import com.wanyun.sfseal.db.mapper.company.LockModelMapper;
import com.wanyun.sfseal.db.model.company.DispenseRecordSearchModel;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.mapper.company.DispenseRecordModelMapper;
import com.wanyun.sfseal.db.model.company.DispenseRecordModel;
import com.wanyun.sfseal.company.service.DispenseRecordModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DispenseRecordModelServiceImpl extends BaseServiceImpl implements DispenseRecordModelService{

    @Resource
    private DispenseRecordModelMapper dispenseRecordModelMapper;

    @Resource
    private CompanyLockXrefModelMapper companyLockXrefModelMapper;

    @Resource
    private LockModelMapper lockModelMapper;

    @Override
    public int deleteByPrimaryKey(Long dispenseRecordId) {
        return dispenseRecordModelMapper.deleteByPrimaryKey(dispenseRecordId);
    }

    @Override
    public int insert(DispenseRecordModel record) {
        return dispenseRecordModelMapper.insert(record);
    }

    @Override
    public int insertSelective(DispenseRecordModel record) {
        return dispenseRecordModelMapper.insertSelective(record);
    }

    @Override
    public DispenseRecordModel selectByPrimaryKey(Long dispenseRecordId) {
        return dispenseRecordModelMapper.selectByPrimaryKey(dispenseRecordId);
    }

    @Override
    public int updateByPrimaryKeySelective(DispenseRecordModel record) {
        return dispenseRecordModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DispenseRecordModel record) {
        return dispenseRecordModelMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<DispenseRecordModel> getDispenseByCondition(DispenseRecordSearchModel condition) {

        Long companyId = this.getCurrentLoginInfo().getCompanyId();
        // 当companyID 为 1 ，则说明为系统管理员
        if (companyId != 1){
            condition.setCompanyId(companyId);
        }
        return dispenseRecordModelMapper.getDispenseRecordsByCondition(condition);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int rollbackDispenseLocks(Long dispenseRecordId) {
        Timestamp currentStamp = new Timestamp(System.currentTimeMillis());
        String currentUserName = this.getCurrentLoginInfo().getAccountName();
        // inactive 分发记录
        DispenseRecordModel recordModel = new DispenseRecordModel();
        recordModel.setDispenseRecordId(dispenseRecordId);
        recordModel.setIsActive(false);
        recordModel.setLastUpdateDate(currentStamp);
        recordModel.setLastUpdateUser(currentUserName);
        int count = dispenseRecordModelMapper.updateByPrimaryKeySelective(recordModel);

        // inactive 关系表 (同时会触发trigger改变锁状态)
        companyLockXrefModelMapper.updateXrefStatusByDispensenRecordId(dispenseRecordId,false,null,currentUserName);

        return count;
    }

    /**
     * 重新分发
     * @param dispenseRecord
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int redispense(DispenseRecordModel dispenseRecord) {
        Timestamp currentStamp = new Timestamp(System.currentTimeMillis());
        String currentUserName = this.getCurrentLoginInfo().getAccountName();
        // 更新分发记录表
        dispenseRecord.setIsActive(true);
        dispenseRecord.setAccpeted(false);
        dispenseRecord.setLastUpdateDate(currentStamp);
        dispenseRecord.setLastUpdateUser(currentUserName);
        int count = dispenseRecordModelMapper.updateByPrimaryKeySelective(dispenseRecord);

        // reactive 关系表 (同时会触发trigger改变锁状态)
        companyLockXrefModelMapper.updateXrefStatusByDispensenRecordId(dispenseRecord.getDispenseRecordId(),true,dispenseRecord.getCompanyId(),currentUserName);
        return count;
    }

    /**
     * 接收分发
     * @param dispenseRecord
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public int receive(DispenseRecordModel dispenseRecord) {
        Timestamp currentStamp = new Timestamp(System.currentTimeMillis());
        String currentUserName = this.getCurrentLoginInfo().getAccountName();
        //更新分发记录状态
        dispenseRecord.setAccpeted(true);
        dispenseRecord.setLastUpdateDate(currentStamp);
        dispenseRecord.setLastUpdateUser(currentUserName);
        int count = dispenseRecordModelMapper.updateByPrimaryKeySelective(dispenseRecord);

        //更新锁的状态
        lockModelMapper.updateLockStatusByCompanyId(dispenseRecord.getCompanyId(),dispenseRecord.getDispenseRecordId(),10003L,currentUserName);

        return count;
    }


}
