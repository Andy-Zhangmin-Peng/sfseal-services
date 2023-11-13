package com.wanyun.sfseal.company.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.model.company.CompanyLockXrefModel;
import com.wanyun.sfseal.db.mapper.company.CompanyLockXrefModelMapper;
import com.wanyun.sfseal.company.service.CompanyLockXrefModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service is the implementation of CompanyDepartmentXrefModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class CompanyLockXrefModelServiceImpl implements CompanyLockXrefModelService {

    @Resource
    private CompanyLockXrefModelMapper companyLockXrefModelMapper;

    @Override
    public int deleteByPrimaryKey(Long xrefId) {
        return companyLockXrefModelMapper.deleteByPrimaryKey(xrefId);
    }

    @Override
    public int insert(CompanyLockXrefModel record) {
        return companyLockXrefModelMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyLockXrefModel record) {
        return companyLockXrefModelMapper.insertSelective(record);
    }

    @Override
    public CompanyLockXrefModel selectByPrimaryKey(Long xrefId) {
        return companyLockXrefModelMapper.selectByPrimaryKey(xrefId);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyLockXrefModel record) {
        return companyLockXrefModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyLockXrefModel record) {
        return companyLockXrefModelMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<String> getAllRfidsByDispenseRecordId(Long dispenseRecordId) {
        return companyLockXrefModelMapper.getAllRfidsByDispenseRecordId(dispenseRecordId);
    }

}

