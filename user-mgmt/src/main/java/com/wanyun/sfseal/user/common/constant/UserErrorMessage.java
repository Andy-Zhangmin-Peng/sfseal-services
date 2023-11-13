/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.common.constant;

/**
 * This class contains error messages on business level within user service
 *
 * @author WanYun
 */
public final class UserErrorMessage {
    public static final String NOT_ACTIVE_USER = "当前登陆人不是一个WEB用户";

    public static final String NOT_ACTIVE_STAFF = "当前登陆人不是一个工作人员";

    public static final String AVATAR_FILE_CANNOT_BE_NONE = "头像文件不能为空";

    public static final String AVATAR_SIZE_OVERSIZE = "头像文件过大,请使用其他图片!";

    public static final String USER_NOT_EXIST = "用户不存在";

    public static final String TAG_MODEL_CANNOT_BE_NONE = "标签信息不能为空";

    public static final String USER_CERTIFIED = "用户已经完成实名认证";

    public static final String IDENTITY_CARD_INCORRECT = "身份证号信息不正确";

    public static final String IDENTITY_CARD_IS_REGISTERED = "该身份证号码已被注册";

    public static final String NONE_PERMISSION_TO_UPDATE_IDENTITY_CARD = "您无权修改该用户的身份证号";

    public static final String NONE_PERMISSION_TO_UPDATE_EMAIL = "您无权修改该用户的邮箱";

    public static final String MOBILE_IS_REQUIRED = "手机号码是必填项";

    public static final String MOBILE_IS_DEDUPLICATE = "手机号码已被注册";

    public static final String EMAIL_IS_MALFORMED = "邮箱格式有误";

    public static final String EMAIL_IS_REGISTERED = "该邮箱已被注册";

    public static final String RECORD_IS_NULL_ERROR = "传入数据不能为空";

    public static final String USER_MODEL_NOT_EXIST = "用户模型不存在";

    public static final String USER_PROFILE_IMAGE_XREF_NOT_EXIST = "用户档案图片不存在";

    public static final String USER_ADDRESS_NOT_EXIST = "用户地址信息不存在";

    public static final String USER_HOBBY_XREF_NOT_EXIST = "用户爱好不存在";

    public static final String STAGING_USER_EXISTS  = "当前用户已导入";
}
