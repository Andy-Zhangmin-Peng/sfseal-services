package com.wanyun.sfseal.user.security.provider.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanyun.sfseal.commonutils.common.service.WechatService;
import com.wanyun.sfseal.commonutils.model.AuthorityModel;
import com.wanyun.sfseal.db.model.user.AccountModel;
import com.wanyun.sfseal.user.security.UserDetailUtils;
import com.wanyun.sfseal.user.service.AccountModelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WanYun
 */
@Log4j2
@Service("wechatIdAuthenticationServiceImpl")
public class WechatIdAuthenticationServiceImpl implements WechatIdAuthenticationService {

    private final WechatService wechatService;
    private final AccountModelService accountService;
    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    public WechatIdAuthenticationServiceImpl(WechatService wechatService, AccountModelService accountService) {
        this.wechatService = wechatService;
        this.accountService = accountService;
    }

    @Override
    public WechatIdAuthenticationToken authenticate(Authentication authentication) {
        JSONObject object = JSON.parseObject(JSON.toJSONString(authentication.getDetails()));
        String usage = object.getString("usage");

        String wechatId = authentication.getPrincipal().toString();
        String authorizationCode = authentication.getCredentials().toString();
        AccountModel accountModel = new AccountModel();
        String openId;

//        try {
//            openId = this.wechatService.getWechatOpenId(authorizationCode);
//            accountModel = this.accountService.getFullAccountByWechatId(openId);
//        } catch (WanYunBusinessException e) {
//            throw new BadCredentialsException("Provided Wechat authorization code not correct.");
//        }
//
//        // 校验查询到的wechat id是否与提供的匹配
//        if (!wechatId.equals(accountModel.getWechatId())) {
//            throw new BadCredentialsException("Provided Wechat id not correct.");
//        }

        log.info("Authenticate with wechatId [{}] and code [{}]", wechatId, authorizationCode);

        List<AuthorityModel> authorityList = UserDetailUtils.assembleAuthorities(accountModel, usage);
        WechatIdAuthenticationToken wechatIdAuthenticationToken = new WechatIdAuthenticationToken(
                accountModel, authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(authorityList));
        wechatIdAuthenticationToken.setDetails(authentication.getDetails());
        return wechatIdAuthenticationToken;
    }
}
