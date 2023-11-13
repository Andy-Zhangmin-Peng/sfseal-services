/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.contants;

/**
 * This class contains error messages on business level within common service
 *
 * @author WanYun
 */
public final class CommonErrorMessage {

    private CommonErrorMessage() {
        throw new UnsupportedOperationException();
    }

    public static class WechatErrorMessage {
        public static final String WECHAT_LOGIN_ERROR = "微信登录异常，请尝试退出小程序后重新进入";
    }

    public static class ExcelErrorMessage {
        public static final String NO_FILE_ERROR = "上传文件不能为空";

        public static final String FILE_TYPE_ERROR = "请上传xlsx类型的文件";

        public static final String FILE_CONFIGURE_ERROR = "上传问及默认配置不正确";

        public static final String NO_SHEET_ERROR = "当前上传文件不存在对应的表单";
    }
}
