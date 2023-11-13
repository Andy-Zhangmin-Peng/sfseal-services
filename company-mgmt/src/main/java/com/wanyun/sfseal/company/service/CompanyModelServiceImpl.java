package com.wanyun.sfseal.company.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import com.wanyun.sfseal.db.model.company.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wanyun.sfseal.db.mapper.company.CompanyModelMapper;
import com.wanyun.sfseal.company.service.CompanyModelService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
/**
 * This service is the implementation of CompanyModelService
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author wanyun
 */
@Service
public class CompanyModelServiceImpl extends BaseServiceImpl implements CompanyModelService{

    @Resource
    private CompanyModelMapper companyModelMapper;

    @Override
    public int deleteByPrimaryKey(Long companyId) {
        return companyModelMapper.deleteByPrimaryKey(companyId);
    }

    @Override
    public int insert(CompanyModel record) {
        return companyModelMapper.insert(record);
    }

    @Override
    public int insertSelective(CompanyModel record) {
        return companyModelMapper.insertSelective(record);
    }

    @Override
    public CompanyModel selectByPrimaryKey(Long companyId) {
        return companyModelMapper.selectByPrimaryKey(companyId);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyModel record) {
        return companyModelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CompanyModel record) {
        return companyModelMapper.updateByPrimaryKey(record);
    }


    /**
     * 获取所有公司信息
     *
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<CompanyModel> getAllcompany() throws WanYunBusinessException {

        return companyModelMapper.selectAll();
    }

    /**
     * 通过条件获取公司信息
     * @param condition
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<CompanyModel> getCompaniesByCondition(CompanySearchModel condition) {
        return companyModelMapper.getCompaniesByCondition(condition);
    }

    /**
     * 创建公司
     * @param company
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public CompanyModel createCompany(CompanyModel company) {
        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());

        company.setCreateDate(currentTimestap);
        company.setLastUpdateDate(currentTimestap);
        company.setLastUpdateUser(this.getCurrentLoginInfo().getAccountName());
        this.insert(company);
        return company;
    }

    /**
     * 更新公司信息
     * @param company
     * @return
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public CompanyModel updateCompany(CompanyModel company) {
        Timestamp currentTimestap = new Timestamp(System.currentTimeMillis());

        company.setLastUpdateDate(currentTimestap);
        company.setLastUpdateUser(this.getCurrentLoginInfo().getAccountName());
        this.updateByPrimaryKey(company);
        return company;
    }

    /**
     * 检查字断值是否已经存在
     * @param valueType
     * @param value
     * @return
     * @throws WanYunBusinessException
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public boolean checkValueExisting(String valueType, String value) throws WanYunBusinessException {
        int count = companyModelMapper.checkValueExisting(valueType, value);
        return count > 0 ? true : false;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
    public List<InventoryModel> getAllInventoryByCondition(InventorySearchModel condition) {
        return companyModelMapper.getAllInventoryByCondition(condition);
    }

    @Override
    public InventoryModel getCurrentyUserInventory() {

        Long companyId = this.getCurrentLoginInfo().getCompanyId();
        InventoryModel inventory = companyModelMapper.getCurrentyUserInventory(companyId);
        return inventory;
    }
}
