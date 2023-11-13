/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.fundation.service;

import com.wanyun.sfseal.db.model.reference.ReferenceDataModel;

import java.util.List;

/**
 * This service is the interface of ReferenceDataServiceImpl
 * A service interface is used to abstract business model/logic and depended by controllers.
 * A serviceImpl class is the actual implementation of the service.
 *
 * @author WanYun
 */
public interface ReferenceDataService {

    /**
     * Get reference data by id
     *
     * @param id the id
     * @return ReferenceDataModel
     */
    ReferenceDataModel getReferenceDataById(Long id);

    /**
     * Get reference data by key and category
     *
     * @param key the key
     * @param category the category
     * @return ReferenceDataModel
     */
    ReferenceDataModel getReferenceDataByKeyAndCategory(String key, String category);

    /**
     * Get reference data by category
     *
     * @param category the category
     * @return List<ReferenceDataModel>
     */
    List<ReferenceDataModel> getReferenceDataByCategory(String category);

    /**
     * Get reference data by language
     *
     * @param language the language
     * @return List<ReferenceDataModel>
     */
    List<ReferenceDataModel> getReferenceDataByLanguage(Integer language);

    /**
     * Get all reference data
     *
     * @return List<ReferenceDataModel>
     */
    List<ReferenceDataModel> getAllReferenceData();

    /**
     * Get all cacheables reference data
     *
     * @return List<ReferenceDataModel>
     */
    List<ReferenceDataModel> getAllCacheablesReferenceData();
}
