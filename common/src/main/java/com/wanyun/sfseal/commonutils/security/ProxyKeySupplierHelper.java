/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.security;

import com.wanyun.sfseal.commonutils.model.VerifierKeyModel;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * This methods take proxy key supplier helper.
 *
 * @author WanYun
 */
@Service
public class ProxyKeySupplierHelper implements Supplier<String> {

    @Override
    public String get() {
        VerifierKeyModel verifierKey = new VerifierKeyModel();
        //TODO
        return verifierKey.getValue();
    }
}
