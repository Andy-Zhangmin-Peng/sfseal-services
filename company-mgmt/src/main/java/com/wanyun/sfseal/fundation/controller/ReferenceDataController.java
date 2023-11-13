/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.fundation.controller;

import com.wanyun.sfseal.db.model.reference.ReferenceDataModel;
import com.wanyun.sfseal.fundation.service.ReferenceDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WanYun
 */
@RestController()
@RequestMapping("/reference")
public class ReferenceDataController {

    private final ReferenceDataService referenceDataService;

    public ReferenceDataController(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    /**
     * Get all reference data
     *
     * @return List<ReferenceDataModel>
     */
    @GetMapping()
    public ResponseEntity<List<ReferenceDataModel>> getAllReferenceData() {
        return ResponseEntity.ok(this.referenceDataService.getAllReferenceData());
    }

    /**
     * Get reference data by id
     *
     * @param id the id
     * @return ReferenceDataModel
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReferenceDataModel> getReferenceDataById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.referenceDataService.getReferenceDataById(id));
    }

    /**
     * Get reference data by key and category
     *
     * @param key      the key
     * @param category the category
     * @return ReferenceDataModel
     */
    @GetMapping(params = {"key", "category"})
    public ResponseEntity<ReferenceDataModel> getReferenceByKeyAndCategory(@RequestParam("key") String key,
                                                                           @RequestParam("category") String category) {
        return ResponseEntity.ok(this.referenceDataService.getReferenceDataByKeyAndCategory(key, category));
    }

    /**
     * Get reference by category
     *
     * @param category the category
     * @return List<ReferenceDataModel>
     */
    @GetMapping(params = "category")
    public ResponseEntity<List<ReferenceDataModel>> getReferenceByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(this.referenceDataService.getReferenceDataByCategory(category));
    }

    /**
     * Get reference by language
     *
     * @param language the language
     * @return List<ReferenceDataModel>
     */
    @GetMapping(params = "language")
    public ResponseEntity<List<ReferenceDataModel>> getReferenceByLanguage(@RequestParam("language") Integer language) {
        return ResponseEntity.ok(this.referenceDataService.getReferenceDataByLanguage(language));
    }
}
