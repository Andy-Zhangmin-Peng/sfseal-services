/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response back from the OAuth2 public key endpoint
 *
 * @author WanYun
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifierKeyModel {

    String alg;
    String value;

    /**
     * @return the alg
     */
    public String getAlg() {
        return alg;
    }

    /**
     * @param alg the alg to set
     */
    public void setAlg(String alg) {
        this.alg = alg;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
