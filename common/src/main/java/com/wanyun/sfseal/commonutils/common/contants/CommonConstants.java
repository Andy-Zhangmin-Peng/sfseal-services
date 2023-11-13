/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.contants;

/**
 * This class contains some base constants
 *
 * @author WanYun
 */
public final class CommonConstants {

    private CommonConstants() {
        throw new UnsupportedOperationException();
    }

    public static final Long EMPTY_LONG_ID = 0L;

    public static final String DATE_FORMAT_YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static final String ENCODING = "UTF-8";

    public static final String ALL_PERMISSION = "ALL_PERMISSION";

    public static final String COLON = ":";

    public static final String SEMICOLON = ";";

    public static final String ERRORCODE = "errcode";

    public static final String ERRMSG = "errmsg";

    public static final String OPENID = "openid";

    public static final String ACCESS_TOKEN = "access_token";


    public static final String TO_USER = "touser";

    public static final String TEMPLATE_ID = "template_id";

    public static final String DATA = "data";

    public static final String SEND_RESULT = "send_result";

    public static final String SUBSCRIBE = "subscribe";

    public static final int TITLE_ROW_NUMBER = 0;

    public static final String EXCEL_FORMAT = ".xlsx";

    public static final String REFERENCE_CACHE_KEY = "REFERENCES_CACHE";


    public static final class Permission {
        public static final String PERMISSION_VIEW_USER_PERSONAL = "PERMISSION_VIEW_USER_PERSONAL";
        public static final String PERMISSION_UPDATE_USER_PERSONAL = "PERMISSION_UPDATE_USER_PERSONAL";
        public static final String PERMISSION_UPDATE_STAFF_PERSONAL = "PERMISSION_UPDATE_STAFF_PERSONAL";
    }

    /**
     * 校验正则
     */
    public static final class Pattern {
        public static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    }

    public enum SUBSCRIBE_MESSAGE_SEND_RESULT {
        /**
         * 推送订阅消息成功
         */
        SUCCESS,
        /**
         * 推送订阅消息失败
         */
        FAILED
    }
}
