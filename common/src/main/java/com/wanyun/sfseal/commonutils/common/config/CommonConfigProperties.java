/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.config;

import com.wanyun.sfseal.commonutils.model.ImportConfigureModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * A properties class mapping to application.yml. Each variable maps to a key of properties file.
 *
 * @author WanYun
 */
@Component
@ConfigurationProperties(prefix = "sfseal")
public class CommonConfigProperties {
    public CommonConfigProperties() {
    }

    public Map<String, List<ImportConfigureModel>> importConfigure;


    public Map<String, List<ImportConfigureModel>> getImportConfigure() {
        return importConfigure;
    }

    public void setImportConfigure(Map<String, List<ImportConfigureModel>> importConfigure) {
        this.importConfigure = importConfigure;
    }
}
