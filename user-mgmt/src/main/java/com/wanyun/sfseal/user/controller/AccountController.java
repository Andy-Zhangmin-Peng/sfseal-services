/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.common.model.PageableModel;
import com.wanyun.sfseal.db.model.user.*;
import com.wanyun.sfseal.user.service.AccountModelService;
import com.wanyun.sfseal.user.service.StaffModelService;
import com.wanyun.sfseal.user.service.UserModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The rest controller exposing API for basic user functions.
 *
 * @author WanYun
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountModelService accountService;

    private final StaffModelService staffModelService;

    private final UserModelService userModelService;

    public AccountController(AccountModelService accountService, StaffModelService staffModelService, UserModelService userModelService) {
        this.accountService = accountService;
        this.staffModelService = staffModelService;
        this.userModelService = userModelService;
    }

    /**
     * Get user by account id
     *
     * @return the account
     * @throws WanYunBusinessException the cloud road business exception
     */
    @GetMapping("")
    public ResponseEntity<AccountModel> getCurrentAccountInfo() throws WanYunBusinessException {
        return ResponseEntity.ok(accountService.getCurrentAccountInfo());
    }

    /**
     * 根据条件查找企业管理员信息
     * @param staffSerchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/staff/query")
    public ResponseEntity<PageInfo<StaffModel>> getStaffsByCondition(@RequestBody PageableModel<StaffSearchModel> staffSerchModel) throws WanYunBusinessException{
        // Construct page helper parameter
        PageHelper.startPage(staffSerchModel.getPageNum(),
                staffSerchModel.getPageSize(),
                staffSerchModel.isCount(),
                staffSerchModel.getReasonable(),
                staffSerchModel.getPageSizeZero());

        List<StaffModel> staffSerachResult = staffModelService.getStaffsByCondition(staffSerchModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(staffSerachResult));
    }

    @GetMapping("/staff/account")
    public ResponseEntity<AccountModel> getFullAccountById(@RequestParam Long accountId) throws WanYunBusinessException {

        return ResponseEntity.ok(accountService.getFullAccountById(accountId));
    }

    /**
     * 更新管理员信息
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("/staff")
    public ResponseEntity<AccountModel> updateStaffIncludeAccount(@RequestBody AccountModel accountModel) throws WanYunBusinessException {
        return ResponseEntity.ok(staffModelService.updateStaffIncludeAccount(accountModel));
    }

    /**
     * 添加一个管理员
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/staff")
    public ResponseEntity<AccountModel> createStaffIncludeAccount(@RequestBody AccountModel accountModel) throws WanYunBusinessException {
        return ResponseEntity.ok(staffModelService.createStaffIncludeAccount(accountModel));
    }

    /**
     * 禁用一个用户
     * @param accountId
     * @return
     * @throws WanYunBusinessException
     */
    @DeleteMapping("/staff/{accountId}")
    public ResponseEntity<Integer> inactiveStaff(@PathVariable("accountId") Long accountId) throws WanYunBusinessException {
        return ResponseEntity.ok(staffModelService.inactiveStaff(accountId));
    }

    /**
     * 检查账号的必要信息是否已经存在
     * @param valueType can be "email","username","mobile","union_id","wechat_id"
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/staff/valuecheck/{valueType}")
    public ResponseEntity<Boolean> checkValueExisting(@PathVariable String valueType, @RequestParam String value)throws WanYunBusinessException{
        return  ResponseEntity.ok(accountService.checkValueExisting(valueType,value));
    }

    /**
     * 根据条件查找企业用户信息
     * @param userSearchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/user/query")
    public ResponseEntity<PageInfo<UserModel>> getUsersByCondition(@RequestBody PageableModel<UserSearchModel> userSearchModel) throws WanYunBusinessException{
        // Construct page helper parameter
        PageHelper.startPage(userSearchModel.getPageNum(),
                userSearchModel.getPageSize(),
                userSearchModel.isCount(),
                userSearchModel.getReasonable(),
                userSearchModel.getPageSizeZero());

        List<UserModel> userSerachResult = userModelService.getUsersByCondition(userSearchModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(userSerachResult));
    }

    /**
     * 添加一个移动端用户
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/user")
    public ResponseEntity<AccountModel> createUserIncludeAccount(@RequestBody AccountModel accountModel) throws WanYunBusinessException {
        return ResponseEntity.ok(userModelService.createUserIncludeAccount(accountModel));
    }

    /**
     * 更新移动端用户
     * @param accountModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("/user")
    public ResponseEntity<AccountModel> updateUserIncludeAccount(@RequestBody AccountModel accountModel) throws WanYunBusinessException {
        return ResponseEntity.ok(userModelService.updateUserIncludeAccount(accountModel));
    }

    /**
     * 禁用一个用户
     * @param accountId
     * @return
     * @throws WanYunBusinessException
     */
    @DeleteMapping("/user/{accountId}")
    public ResponseEntity<Integer> inactiveUser(@PathVariable("accountId") Long accountId) throws WanYunBusinessException {
        return ResponseEntity.ok(userModelService.inactiveUser(accountId));
    }
}
