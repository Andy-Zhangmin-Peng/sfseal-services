/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.common.constant;

/**
 * This class contains some base constants
 *
 * @author WanYun
 */
public final class UserConstants {

    public static final int REAL_USER_INFO = 2;

    public static final String EXPORT_CONFIGURE = "staging-user";

    // 综合信息
    public static final String COMPREHENSIVE_DATA = "comprehensive_data";

    // 列表数据
    public static final String PAGE_LIST = "page_list";

    /** 用户头像上传图片最大字节数 **/
    public static final long MAX_AVATAR_UPLOAD_SIZE = 2097152L;

    public static final class RoleUsage {
        public static final String END_USER = "end_user";
        public static final String SYSTEM_USER = "system_user";
    }
}
