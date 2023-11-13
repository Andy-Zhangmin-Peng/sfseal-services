/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.db.common.constant;

/**
 * This class contains error messages on business level within user service
 *
 * @author WanYun
 */
public final class DbErrorMessage {
    public static final String NO_CREATE_DATE_ERROR = "创建时间是必填项.";
    public static final String NO_CREATE_USER_ERROR = "创建人是必填项.";
    public static final String NO_LAST_UPDATE_DATE_ERROR = "最后更新时间是必填项.";
    public static final String NO_LAST_UPDATE_USER_ERROR = "最后更新人是必填项.";
    public static final String NO_XREF_ID_ERROR = "关联信息是必填项.";

    public static final class User {
        public static final String USER_NOT_EXIST_ERROR = "用户信息不存在.";

        public static final String NO_ACCOUNT_ID_ERROR = "帐户ID是必填项.";
        public static final String NO_ACCOUNT_USER_INFO_ERROR = "帐户信息是必填项.";
        public static final String NO_ACCOUNT_USERNAME_ERROR = "帐户名称是必填项.";
        public static final String NO_ACCOUNT_PASSWORD_ERROR = "帐户密码是必填项.";
        public static final String NO_WECHAT_ID_ERROR = "无微信OpenId.";
        public static final String NO_MOBILE_ERROR = "手机号是必填项.";
        public static final String NO_EMAIL_ERROR = "邮箱是必填项.";
        public static final String MOBILE_IS_EXISTS= "手机号已存在.";
        public static final String EMAIL_IS_EXISTS= "邮箱已存在.";

        public static final String NO_USER_ID_ERROR = "帐户相关人ID是必填项.";
        public static final String NO_USER_NAME_ERROR = "用户昵称是必填项.";

        public static final String NO_STAGING_USER_ID_ERROR = "被邀请用户ID是必填项.";
        public static final String NO_STAGING_USER_MOBILE_ERROR = "被邀请用户电话是必填项.";
        public static final String NO_STAGING_USER_COMMUNITY_ID_ERROR = "被邀请用户关联社群是必填项.";
        public static final String NO_STAGING_USER_REGISTERED_ERROR = "被邀请用户的状态是必填项.";

        public static final String NO_STAFF_IS_ACTIVE_ERROR = "帐户相关人状态是必填项.";

        public static final String NO_PERMISSION_ID_ERROR = "权限ID是必填项.";
        public static final String NO_PERMISSION_NAME_ERROR = "权限名称是必填项.";

        public static final String NO_ROLE_ID_ERROR = "角色ID是必填项.";
        public static final String NO_ROLE_NAME_ERROR = "角色名称是必填项.";
        public static final String NO_ROLE_USAGE_ERROR = "角色适用范围是必填项.";
        public static final String NO_CHILD_ROLE_ID_ERROR = "子角色ID是必填项.";

        public static final String NO_ACCOUNT_ROLE_XREF_ID_ERROR = "角色帐户关系ID是必填项.";
        public static final String NO_ROLE_HIERARCHY_XREF_ID_ERROR = "角色关系ID是必填项.";
        public static final String NO_ROLE_PERMISSION_XREF_ID_ERROR = "帐户权限关系ID是必填项.";

        public static final String NO_USER_PICTURE_ERROR = "账户档案图片是必填项.";
        public static final String NO_USER_PICTURE_COMPRESS_RATE_ERROR = "档案图片压缩比是必填项.";

        public static final String NO_FEEDBACK_PICTURE_PATH_ERROR = "意见反馈图片路径是必填项.";

        public static final String NO_FEEDBACK_TITLE_ERROR = "意见反馈标题是必填项.";
        public static final String NO_FEEDBACK_CONTENT_ERROR = "意见反馈内容是必填项.";

        public static final String NO_TAG_ID_ERROR = "标签ID是必填项.";
        public static final String NO_TAG_ADDER_ACCOUNT_ID_ERROR = "标签添加人账户ID是必填项.";
        public static final String NO_TAG_TARGET_ACCOUNT_ID_ERROR = "标签被添加人账户ID是必填项.";
        public static final String NO_TAG_NAME_ERROR = "标签名称是必填项.";
        public static final String NO_TAG_TYPE_ERROR = "标签归属类型是必填项.";

        public static final String NO_HOBBY_ID_ERROR = "爱好ID是必填项.";
        public static final String NO_HOBBY_NAME_ERROR = "爱好名称是必填项.";

        public static final String NO_CAPTCHA_MOBILE_NUMBER_ERROR = "验证手机号是必填项.";
        public static final String NO_CAPTCHA_CAPTCHA_ACTION_TYPE_ERROR = "验证码类型是必填项.";
        public static final String NO_CAPTCHA_CAPTCHA_NUMBER_ERROR = "验证码内容是必填项.";
        public static final String NO_CAPTCHA_EXPIRED_DATE_ERROR = "验证码过期时间是必填项.";
    }

    public static final class Community {
        public static final String NO_OPERATOR_ID_ERROR = "运营商ID是必填项.";
        public static final String NO_OPERATOR_NAME_ERROR = "运营商名称是必填项.";

        public static final String NO_OPERATOR_APP_ID_ERROR = "运营商AppID是必填项.";
        public static final String NO_OPERATOR_WECHAT_NUMBER_ERROR = "运营商微信号是必填项.";
        public static final String NO_OPERATOR_PUBLIC_ACCOUNT_NAME_ERROR = "运营商公众号名是必填项.";
        public static final String NO_OPERATOR_IMAGE_WATERING_ERROR = "运营商公众号图片水印方式是必填项.";

        public static final String NO_COMMUNITY_ID_ERROR = "社群ID是必填项.";
        public static final String NO_COMMUNITY_NAME_ERROR = "社群名称是必填项.";

        public static final String NO_ACTIVITY_ID_ERROR = "活动ID是必填项.";
        public static final String NO_ACTIVITY_TITLE_ERROR = "活动标题是必填项.";
        public static final String NO_ACTIVITY_TYPE_ERROR = "活动类型是必填项.";
        public static final String NO_ACTIVITY_DETAILS = "活动详情是必填项.";
        public static final String NO_ACTIVITY_START_DATE_ERROR = "活动开始日期是必填项";
        public static final String NO_ACTIVITY_END_DATE_ERROR = "活动结束日期是必填项";
        public static final String NO_ACTIVITY_CITY_ERROR = "活动城市是必填项";
        public static final String NO_ACTIVITY_AREA_ERROR = "活动地区是必填项";
        public static final String NO_ACTIVITY_STREET_ERROR = "活动详细地址是必填项";
        public static final String NO_ACTIVITY_CHARGE_TYPE_ERROR = "活动费用类型是必填项";

        public static final String NO_ACTIVITY_COMMUNITY_XREF_ID_ERROR = "活动社群关系ID是必填项.";
        public static final String NO_ACTIVITY_USER_XREF_ID_ERROR = "活动用户关系ID是必填项.";
        public static final String NO_COMMUNITY_ACCOUNT_XREF_ID_ERROR = "运营商用户关系ID是必填项.";
        public static final String NO_COMMUNITY_REGISTERED_USER_XREF_ID_ERROR = "运营商账户关系ID是必填项.";
        public static final String NO_OPERATOR_COMMUNITY_XREF_ID_ERROR = "运营商社群关系ID是必填项.";
        public static final String NO_OPERATOR_ACCOUNT_XREF_ID_ERROR = "社群用户关系是必填项.";

        public static final String NO_ACTIVITY_ADDRESS_ID_ERROR = "活动地址ID是必填项.";
        public static final String NO_ACTIVITY_ADDRESS_TYPE_CODE_ERROR = "活动类型编码是必填项.";

        public static final String NO_ACTIVITY_RELATIONSHIP_TYPE = "活动关系类型是必填项.";
    }

    public static final class Reference {
        public static final String NO_IMAGE_ID_ERROR = "图片ID是必填项";
        public static final String NO_IMAGE_PATH_ERROR = "图片路径是必填项";
        public static final String NO_IMAGE_BLOB_ERROR = "图片内容是必填项";
    }

    public static final class Wechat {
        public static final String WECHAT_LOGIN_ERROR = "微信登录异常，请尝试退出小程序后重新进入";
    }
}
