/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.commonutils.common.service;

import com.wanyun.sfseal.commonutils.common.config.WechatProperties;
import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.common.contants.CommonErrorMessage;
import com.wanyun.sfseal.commonutils.common.contants.UrlConstants;
import com.wanyun.sfseal.commonutils.common.proxy.RestfulWebClient;
import com.wanyun.sfseal.commonutils.exception.WanYunBusinessException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;


/**
 * @author WanYun
 */
@Service
public class WechatServiceImpl implements WechatService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WechatProperties wechatProperties;

    private final RestfulWebClient wechatRestfulWebClient;

    public WechatServiceImpl(WechatProperties wechatProperties,
                             @Qualifier("wechatRestfulWebClient") RestfulWebClient wechatRestfulWebClient) {
        this.wechatProperties = wechatProperties;
        this.wechatRestfulWebClient = wechatRestfulWebClient;
    }


    @Override
    public String getWechatOpenId(String code) throws WanYunBusinessException {
        // Send request to get openId by login code
        String response = this.wechatRestfulWebClient.getRequest(uriBuilder -> uriBuilder.path(UrlConstants.WechatUrlConstants.JS_CODE_SESSION_URI)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.APP_ID, wechatProperties.getAppId())
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.SECRET, wechatProperties.getAppSecret())
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.JS_CODE, code)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.GRANT_TYPE, UrlConstants.WechatUrlConstants.WechatParamConstants.AUTHORIZATION_CODE)
                .build(), String.class).block();
        logger.info(response);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.has(CommonConstants.ERRORCODE)) {
            // 调用微信API获取用户openId失败
            logger.error("微信获取用户openId失败, 错误信息: [{}]", jsonObject.getString(CommonConstants.ERRMSG));
            throw new WanYunBusinessException(CommonErrorMessage.WechatErrorMessage.WECHAT_LOGIN_ERROR);
        }

        return jsonObject.getString(CommonConstants.OPENID);
    }

    @Override
    public String getWechatAccessToken() throws WanYunBusinessException {
        // TODO：因为access_token的有效期目前是两个小时，需定时刷新，重复获取将导致上次获取的access_token失效，
        //  所以要把access_token存储到Redis里面。
        // Send request to get access token
        String response = this.wechatRestfulWebClient.getRequest(uriBuilder -> uriBuilder.path(UrlConstants.WechatUrlConstants.GET_ACCESS_TOKEN_URI)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.GRANT_TYPE, UrlConstants.WechatUrlConstants.WechatParamConstants.CLIENT_CREDENTIAL)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.APP_ID, wechatProperties.getAppId())
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.SECRET, wechatProperties.getAppSecret())
                .build(), String.class).block();
        logger.info(response);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.has(CommonConstants.ERRORCODE)) {
            // 调用微信API获取用access_token失败
            logger.error("微信获取access_token失败, 错误信息: [{}]", jsonObject.getString(CommonConstants.ERRMSG));
            throw new WanYunBusinessException(CommonErrorMessage.WechatErrorMessage.WECHAT_LOGIN_ERROR);
        }
        return jsonObject.getString(CommonConstants.ACCESS_TOKEN);
    }

    @Override
    public JSONObject sendWechatSubscribeMessage(String toUser, String templateId, JSONObject data, String token) {
        JSONObject params = new JSONObject();
        params.put(CommonConstants.TO_USER, toUser);
        params.put(CommonConstants.TEMPLATE_ID, templateId);
        params.put(CommonConstants.DATA, data);
        JSONObject response = this.wechatRestfulWebClient.postRequest(uriBuilder -> uriBuilder.path(UrlConstants.WechatUrlConstants.POST_SEND_SUBSCRIBE_MESSAGE_URI)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.ACCESS_TOKEN, token)
                .build(), BodyInserters.fromValue(params), JSONObject.class).block();
        if (response.has(CommonConstants.ERRORCODE)) {
            logger.error("推送微信订阅消息失败，错误信息: [{}]", response.getString(CommonConstants.ERRMSG));
            response.put(CommonConstants.SEND_RESULT, CommonConstants.SUBSCRIBE_MESSAGE_SEND_RESULT.FAILED);
        } else {
            response.put(CommonConstants.SEND_RESULT, CommonConstants.SUBSCRIBE_MESSAGE_SEND_RESULT.SUCCESS);
        }
        response.put(CommonConstants.TO_USER, toUser);
        response.put(CommonConstants.TEMPLATE_ID, templateId);
        response.put(CommonConstants.ACCESS_TOKEN, token);

        return response;
    }

    @Override
    public Integer subscribeState(String openId, String token) {
        JSONObject response = this.wechatRestfulWebClient.getRequest(uriBuilder -> uriBuilder.path(UrlConstants.WechatUrlConstants.GET_USER_INFO_URI)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.ACCESS_TOKEN, token)
                .queryParam(UrlConstants.WechatUrlConstants.WechatParamConstants.OPEN_ID, openId)
                .build(), JSONObject.class).block();

        if (response.has(CommonConstants.ERRORCODE)) {
            logger.error("获取用户是否订阅失败，错误信息: [{}]", response.getString(CommonConstants.ERRMSG));
        } else {
            // 用户是否订阅该公众号标识（0代表此用户没有关注该公众号 1表示关注了该公众号）。
            return (Integer) response.get(CommonConstants.SUBSCRIBE);
        }

        return -1;
    }
}
