/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.exception;

/**
 * This class represents the exceptions on business level within legal entity
 * microservice
 *
 * @author WanYun
 */
@SuppressWarnings("serial")
public class WanYunBusinessException extends Exception {

    private String[] errorCodeArray;

    private String errorMessage;

    private Exception originalException;

    public WanYunBusinessException() {
    }

    public WanYunBusinessException(Exception e) {
        setOriginalException(e);
    }

    public WanYunBusinessException(Exception e, String errorMessage) {
        setOriginalException(e);
        setErrorMessage(errorMessage);
    }

    public WanYunBusinessException(String[] errorCodeArray) {
        setErrorCodeArray(errorCodeArray);
    }

    public WanYunBusinessException(String errorMessage) {
        super(errorMessage);
        setErrorMessage(errorMessage);
    }

    public WanYunBusinessException(String[] errorCodeArray, String errorMessage) {
        super(errorMessage);
        setErrorCodeArray(errorCodeArray);
        setErrorMessage(errorMessage);
    }

    public WanYunBusinessException(Exception e, String[] errorCodeArray) {
        if (null != e) {
            setOriginalException(e);
            setErrorMessage(e.getMessage());
        }
        setErrorCodeArray(errorCodeArray);
    }

    /**
     * @return the errorCodeArray
     */
    public String[] getErrorCodeArray() {
        return errorCodeArray;
    }

    /**
     * @param errorCodeArray the errorCodeArray to set
     */
    public void setErrorCodeArray(String[] errorCodeArray) {
        this.errorCodeArray = errorCodeArray;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the originalException
     */
    public Exception getOriginalException() {
        return originalException;
    }

    /**
     * @param originalException the originalException to set
     */
    public void setOriginalException(Exception originalException) {
        this.originalException = originalException;
    }
}
