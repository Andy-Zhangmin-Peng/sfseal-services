/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.contants;

/**
 * @author WanYun
 */
public final class UrlConstants {

    private UrlConstants() {
        throw new UnsupportedOperationException();
    }

    public static class WechatUrlConstants {
        public static final String WECHAT_API_BASE_URL = "https://api.weixin.qq.com";
        public static final String JS_CODE_SESSION_URI = "/sns/jscode2session";
        public static final String GET_ACCESS_TOKEN_URI = "/cgi-bin/token";
        public static final String POST_SEND_SUBSCRIBE_MESSAGE_URI = "/cgi-bin/message/subscribe/send";
        public static final String GET_USER_INFO_URI = "/cgi-bin/user/info";

        public static class WechatParamConstants {
            public static final String APP_ID = "appid";
            public static final String SECRET = "secret";
            public static final String JS_CODE = "js_code";
            public static final String GRANT_TYPE = "grant_type";
            public static final String AUTHORIZATION_CODE = "authorization_code";
            public static final String CLIENT_CREDENTIAL = "client_credential";
            public static final String ACCESS_TOKEN = "access_token";
            public static final String OPEN_ID = "openid";
        }
    }

}
