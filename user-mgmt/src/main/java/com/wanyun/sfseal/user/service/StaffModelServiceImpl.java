/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.user.service;

import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.mapper.company.CompanyAccountXrefModelMapper;
import com.wanyun.sfseal.db.mapper.user.AccountModelMapper;
import com.wanyun.sfseal.db.mapper.user.AccountRoleXrefModelMapper;
import com.wanyun.sfseal.db.mapper.user.StaffModelMapper;
import com.wanyun.sfseal.db.mapper.user.UserModelMapper;
import com.wanyun.sfseal.db.model.company.CompanyAccountXrefModel;
import com.wanyun.sfseal.db.model.user.*;
import com.wanyun.sfseal.user.security.password.WanYunPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
/**
 * This service is the implementation of StaffModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class StaffModelServiceImpl extends BaseServiceImpl implements StaffModelService {

    @Resource
    private StaffModelMapper staffModelMapper;

    @Resource
    private AccountModelMapper accountModelMapper;

    @Resource
    private AccountRoleXrefModelMapper accountRoleXrefModelMapper;

    @Resource
    private CompanyAccountXrefModelMapper companyAccountXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long accountId) {
        return staffModelMapper.deleteByPrimaryKey(accountId);
    }

    @Override
    public int insert(StaffModel record) {
        return staffModelMapper.insert(record);
    }

    @Override
    public int insertSelective(StaffModel record) {
        return staffModelMapper.insertSelective(record);
    }

    @Override
    public StaffModel selectByPrimaryKey(Long accountId) {
        return staffModelMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public int updateByPrimaryKeySelective(StaffModel record) {
        return staffModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(StaffModel record) {
        return staffModelMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<StaffModel> getStaffsByCondition(StaffSearchModel condition) {
        Long companyId = this.getCurrentLoginInfo().getCompanyId();
        if (companyId != 1){
            condition.getCompanyIdList().add(companyId);
        }
        return staffModelMapper.getStaffsByCondition(condition) ;
    }

    /**
     * 更新一个企业管理员
     *
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public AccountModel updateStaffIncludeAccount(AccountModel accountModel) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();

        AccountModel existingAccount = this.accountModelMapper.selectByPrimaryKey(accountModel.getAccountId());

        if (!CommonUtil.compareObjects(existingAccount.getPassword(), accountModel.getPassword())){
            accountModel.setPassword(getPassword(accountModel.getPassword()));
        }
        //向数据库更新Account信息
        accountModel.setLastUpdateDate(currentTimeStamp);
        accountModel.setLastUpdateUser(currentAccountName);

        accountModelMapper.updateByPrimaryKey(accountModel);

        //向数据库添加Account和Role的关系
        if (!CommonUtil.isListEmpty(accountModel.getRoles())){
            accountModel.getRoles().forEach(roleModel -> {
                AccountRoleXrefModel accountRoleXrefModel = new AccountRoleXrefModel();
                accountRoleXrefModel.setAccountId(accountModel.getAccountId());
                accountRoleXrefModel.setRoleId(roleModel.getRoleId());
                accountRoleXrefModel.setCreateDate(currentTimeStamp);
                accountRoleXrefModel.setLastUpdateDate(currentTimeStamp);
                accountRoleXrefModel.setLastUpdateUser(currentAccountName);
                accountRoleXrefModelMapper.upsert(accountRoleXrefModel);
            });
        }

        // 给公司账号添加详细信息
        StaffModel staffModel = accountModel.getStaff();
        staffModel.setAccountId(accountModel.getAccountId());
        staffModel.setLastUpdateDate(currentTimeStamp);
        staffModel.setLastUpdateUser(currentAccountName);
        staffModel.setIsActive(true);
        staffModelMapper.updateByPrimaryKey(staffModel);

        return accountModel;
    }

    /**
     * 创建管理员账号
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public AccountModel createStaffIncludeAccount(AccountModel accountModel) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();
        accountModel.setCreateDate(currentTimeStamp);
        accountModel.setPassword(getPassword(accountModel.getPassword()));
        accountModel.setLastUpdateDate(currentTimeStamp);
        accountModel.setLastUpdateUser(currentAccountName);
        accountModelMapper.insert(accountModel);

        //向数据库添加Account和公司关系
        CompanyAccountXrefModel companyAccountXrefModel = new CompanyAccountXrefModel();
        companyAccountXrefModel.setAccountId(accountModel.getAccountId());
        companyAccountXrefModel.setCompanyId(accountModel.getCompany().getCompanyId());
        companyAccountXrefModel.setCreateDate(currentTimeStamp);
        companyAccountXrefModel.setLastUpdateDate(currentTimeStamp);
        companyAccountXrefModel.setLastUpdateUser(currentAccountName);
        companyAccountXrefModelMapper.insert(companyAccountXrefModel);

        //TODO ：改为批量插入
        //向数据库添加Account和Role的关系
        if (!CommonUtil.isListEmpty(accountModel.getRoles())){
            accountModel.getRoles().forEach(roleModel -> {
                AccountRoleXrefModel accountRoleXrefModel = new AccountRoleXrefModel();
                accountRoleXrefModel.setAccountId(accountModel.getAccountId());
                accountRoleXrefModel.setRoleId(roleModel.getRoleId());
                accountRoleXrefModel.setCreateDate(currentTimeStamp);
                accountRoleXrefModel.setLastUpdateDate(currentTimeStamp);
                accountRoleXrefModel.setLastUpdateUser(currentAccountName);
                accountRoleXrefModelMapper.upsert(accountRoleXrefModel);
            });
        }

        // 给社群账号添加详细信息
        StaffModel staffModel = accountModel.getStaff();
        staffModel.setAccountId(accountModel.getAccountId());
        staffModel.setCreateDate(currentTimeStamp);
        staffModel.setLastUpdateDate(currentTimeStamp);
        staffModel.setLastUpdateUser(currentAccountName);
        staffModel.setIsActive(true);
        staffModelMapper.insert(staffModel);

        return accountModel;
    }

    @Override
    public int inactiveStaff(Long accountId) throws WanYunBusinessException {
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
        String currentAccountName = this.getCurrentLoginInfo().getAccountName();

        StaffModel staffModel = new StaffModel();
        staffModel.setIsActive(false);
        staffModel.setAccountId(accountId);
        staffModel.setLastUpdateDate(currentTimeStamp);
        staffModel.setLastUpdateUser(currentAccountName);
        staffModelMapper.updateByPrimaryKeySelective(staffModel);
        return staffModelMapper.updateByPrimaryKeySelective(staffModel);
    }

    /**
     * salt password
     *
     * @param password
     * @return
     */
    public String getPassword(String password) {
        WanYunPasswordEncoder passwordEncoder = new WanYunPasswordEncoder("WanYun");
        String encodePassword = passwordEncoder.encodePurePassword(password);
        return encodePassword;
    }
}
