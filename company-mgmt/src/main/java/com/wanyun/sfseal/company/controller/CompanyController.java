/*
 * Copyright (c) 2020. WanYun Corporation. All Rights Reserved.
 */

package com.wanyun.sfseal.company.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.company.service.CompanyModelService;
import com.wanyun.sfseal.company.service.DepartmentModelService;
import com.wanyun.sfseal.db.common.model.PageableModel;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The rest controller exposing API for retrieving and maintaining Company.
 *
 * @author WanYun
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final DepartmentModelService departmentModelService;

    private final CompanyModelService companyModelService;

    public CompanyController(DepartmentModelService departmentModelService, CompanyModelService companyModelService) {
        this.departmentModelService = departmentModelService;
        this.companyModelService = companyModelService;
    }


    /**
     * 查找所有公司信息
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<CompanyModel>> getAllCompany() throws WanYunBusinessException {
        return ResponseEntity.ok(companyModelService.getAllcompany());
    }

    /**
     * 根据条件查找公司信息
     * @param companSearchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/query")
    public ResponseEntity<PageInfo<CompanyModel>> queryCompanyByCondition (@RequestBody PageableModel<CompanySearchModel> companSearchModel) throws WanYunBusinessException {
        PageHelper.startPage(companSearchModel.getPageNum(),
                companSearchModel.getPageSize(),
                companSearchModel.isCount(),
                companSearchModel.getReasonable(),
                companSearchModel.getPageSizeZero());

        List<CompanyModel> companyModels = companyModelService.getCompaniesByCondition(companSearchModel.getCondition());

        return ResponseEntity.ok(new PageInfo<>(companyModels));
    }

    /**
     * 创建公司
     * @param companyModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("")
    public ResponseEntity<CompanyModel> createCompany(@RequestBody CompanyModel companyModel) throws WanYunBusinessException {
        return ResponseEntity.ok(companyModelService.createCompany(companyModel));
    }

    /**
     * 更新公司信息
     * @param companyModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("")
    public ResponseEntity<CompanyModel> updateCompany(@RequestBody CompanyModel companyModel) throws WanYunBusinessException {
        return ResponseEntity.ok(companyModelService.updateCompany(companyModel));
    }

    /**
     * 根据条件查找部门信息
     *
     * @param departmentSearchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/department/query")
    public ResponseEntity<PageInfo<DepartmentModel>> getAllDepartmentsByCondition (@RequestBody PageableModel<DepartmentSearchModel> departmentSearchModel) throws WanYunBusinessException{
        PageHelper.startPage(departmentSearchModel.getPageNum(),
                departmentSearchModel.getPageSize(),
                departmentSearchModel.isCount(),
                departmentSearchModel.getReasonable(),
                departmentSearchModel.getPageSizeZero());

        List<DepartmentModel> departmentModels = departmentModelService.getDepartmentsByCondition(departmentSearchModel.getCondition());
        return ResponseEntity.ok(new PageInfo<>(departmentModels));
    }

    /**
     * 创建部门
     * @param departmentModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/department")
    public ResponseEntity<DepartmentModel> createDepartment (@RequestBody @Valid DepartmentModel departmentModel) throws WanYunBusinessException {
        return ResponseEntity.ok(departmentModelService.createDepartment(departmentModel));
    }

    /**
     * 更新部门
     *
     * @param departmentModel
     * @return
     * @throws WanYunBusinessException
     */
    @PutMapping("/department")
    public ResponseEntity<DepartmentModel> updateDepartment(@RequestBody @Valid DepartmentModel departmentModel) throws WanYunBusinessException {
        return ResponseEntity.ok(departmentModelService.updateDepartment(departmentModel));
    }

    /**
     * 检查部门必要字断是否存在
     * @param valueType
     * @param value include departmentName,phoneNumber
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/department/valuecheck/{valueType}")
    public ResponseEntity<Boolean> checkDepartmentValueExisting(@PathVariable String valueType, @RequestParam String value)throws WanYunBusinessException{
        return  ResponseEntity.ok(departmentModelService.checkValueExisting(valueType,value));
    }

    /**
     * 检查公司必要字断是否存在
     * @param valueType
     * @param value include email companyName phoneNumber
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/valuecheck/{valueType}")
    public ResponseEntity<Boolean> checkCompanyValueExisting(@PathVariable String valueType, @RequestParam String value)throws WanYunBusinessException{
        return  ResponseEntity.ok(companyModelService.checkValueExisting(valueType,value));
    }

    /**
     * 根据条件查找库存信息
     *
     * @param inventorySearchModel
     * @return
     * @throws WanYunBusinessException
     */
    @PostMapping("/inventory/query")
    public ResponseEntity<PageInfo<InventoryModel>> getAllInventoryByCondition (@RequestBody PageableModel<InventorySearchModel> inventorySearchModel) throws WanYunBusinessException{
        PageHelper.startPage(inventorySearchModel.getPageNum(),
                inventorySearchModel.getPageSize(),
                inventorySearchModel.isCount(),
                inventorySearchModel.getReasonable(),
                inventorySearchModel.getPageSizeZero());
        PageHelper.clearPage();
        List<InventoryModel> inventoryModels = companyModelService.getAllInventoryByCondition(inventorySearchModel.getCondition());

        return ResponseEntity.ok(new PageInfo<>(inventoryModels));
    }

    /**
     * 获取当前登录公司库存信息
     * @return
     * @throws WanYunBusinessException
     */
    @GetMapping("/inventory")
    public ResponseEntity<InventoryModel> getCurrentyUserInventory() throws WanYunBusinessException{
        return ResponseEntity.ok(companyModelService.getCurrentyUserInventory());
    }
}
