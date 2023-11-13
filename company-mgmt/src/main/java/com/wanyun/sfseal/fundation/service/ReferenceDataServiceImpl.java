/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.fundation.service;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.db.mapper.reference.ReferenceDataModelMapper;
import com.wanyun.sfseal.db.model.reference.ReferenceDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WanYun
 */
@Service
@Transactional(value = "transactionManager", rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, timeout = 300)
public class ReferenceDataServiceImpl implements ReferenceDataService {

    private ReferenceDataModelMapper referenceDataModelMapper;

    @Autowired
    public ReferenceDataServiceImpl(ReferenceDataModelMapper referenceDataModelMapper) {
        this.referenceDataModelMapper = referenceDataModelMapper;
    }

    @Override
    public ReferenceDataModel getReferenceDataById(Long id) {
        return this.referenceDataModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public ReferenceDataModel getReferenceDataByKeyAndCategory(String key, String category) {
        return this.referenceDataModelMapper.getReferenceDataByKeyAndCategory(key, category);
    }

    @Override
    public List<ReferenceDataModel> getReferenceDataByCategory(String category) {
        return this.referenceDataModelMapper.getReferenceDataByCategory(category);
    }

    @Override
    public List<ReferenceDataModel> getReferenceDataByLanguage(Integer language) {
        return this.referenceDataModelMapper.getReferenceDataByLanguage(language);
    }

    @Override
    public List<ReferenceDataModel> getAllReferenceData() {
        return this.referenceDataModelMapper.selectAll();
    }

    @Override
    @Cacheable(CommonConstants.REFERENCE_CACHE_KEY)
    public List<ReferenceDataModel> getAllCacheablesReferenceData() {
        return this.referenceDataModelMapper.selectAll();
    }
}
