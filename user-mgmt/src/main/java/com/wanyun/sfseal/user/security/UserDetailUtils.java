package com.wanyun.sfseal.user.security;

import com.wanyun.sfseal.commonutils.common.contants.CommonConstants;
import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import com.wanyun.sfseal.commonutils.model.AuthorityModel;
import com.wanyun.sfseal.db.model.user.AccountModel;
import com.wanyun.sfseal.user.common.constant.UserConstants;
import com.wanyun.sfseal.user.common.constant.UserErrorMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import java.util.ArrayList;
import java.util.List;

/**
 * 与Spring Security 用户信息获取相关的工具类
 * @author WanYun
 */
@Log4j2
public class UserDetailUtils {

    private UserDetailUtils() throws IllegalAccessException {
        throw new IllegalAccessException("UserDetailUtils could not be new.");
    }

    /**
     * 从Account数据中抽取与授权相关的内容并组成Token所需要的权限列表
     *
     * @param account
     * @param usage
     * @return
     * @throws AuthenticationException
     */
    public static List<AuthorityModel> assembleAuthorities(AccountModel account, String usage) throws AuthenticationException {
        boolean isSystemUser = CommonUtil.compareObjects(UserConstants.RoleUsage.SYSTEM_USER, usage);

        Long companyId = CommonConstants.EMPTY_LONG_ID;
        Long userId = CommonConstants.EMPTY_LONG_ID;
        if (isSystemUser) {
            if (account.getStaff() == null) {
                if (account.getUser() != null) {
                    log.error(UserErrorMessage.NOT_ACTIVE_STAFF);
                    throw new UserDeniedAuthorizationException(UserErrorMessage.NOT_ACTIVE_STAFF);
                }
            } else {
                if (account.getStaff().getCompany() != null && account.getStaff().getCompany().getCompanyId() != null) {
                    companyId = account.getStaff().getCompany().getCompanyId();
                }
            }
        } else {
            if (account.getUser() == null) {
                log.error(UserErrorMessage.NOT_ACTIVE_USER);
                throw new UserDeniedAuthorizationException(UserErrorMessage.NOT_ACTIVE_USER);
            } else {
                userId = account.getUser().getUserId();
                companyId = account.getCompany().getCompanyId();
            }
        }

        List<AuthorityModel> authorityList = new ArrayList<>();
        if (!CommonUtil.isListEmpty(account.getPermissions(usage))) {
            Long finalCompanyId = companyId;
            Long finalUserId = userId;
            account.getPermissions(usage).forEach(permission -> {
                AuthorityModel authTemp = new AuthorityModel();
                authTemp.setPermissionId(permission.getPermissionId());
                authTemp.setPermissionName(permission.getPermissionName());
                authTemp.setAccountId(account.getAccountId());
                authTemp.setAccountName(account.getUsername());
                authTemp.setCompanyId(finalCompanyId);
                authTemp.setUserId(finalUserId);
                authorityList.add(authTemp);
            });
        } else {
            log.error("The user don not have any permission");
        }

        return authorityList;
    }
}
