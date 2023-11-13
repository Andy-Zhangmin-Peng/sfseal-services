/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is a model for capturing error response details relating to an invalid token.
 *
 * @author WanYun
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenErrorModel {
    /**
     * The error message indicating the token error
     */
    private String error;

    /**
     * A description of the token error
     */
    private String errorDescription;

    /**
     * @return the error
     */
    public String getError() {
        return this.error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the errorDescription
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription the errorDescription to set
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
