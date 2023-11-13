/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.service;

import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import org.json.JSONObject;

/**
 *
 * @author WanYun
 */
public interface WechatService {

    /**
     * Get user's wechat openId
     *
     * @param code the code
     * @return String
     * @throws WanYunBusinessException the WanYunBusinessException
     */
    String getWechatOpenId(String code) throws WanYunBusinessException;

    /**
     * Get wechat access token
     *
     * @return String
     * @throws WanYunBusinessException the WanYunBusinessException
     */
    String getWechatAccessToken() throws WanYunBusinessException;

    /**
     * 发送微信订阅消息
     *
     * @param toUser 接受者（用户）的openid
     * @param templateId 所需下发的订阅模板id
     * @param data 模板内容
     * @param token 接口调用凭证
     * @return JSONObject
     */
    JSONObject sendWechatSubscribeMessage(String toUser,
                                          String templateId,
                                          JSONObject data,
                                          String token);

    /**
     * 根据微信openId 获取用户订阅状态
     *
     * @param openId 微信openId
     * @param token 接口调用凭证
     * @return 是否订阅该公众号标识
     */
    Integer subscribeState(String openId, String token);
}
