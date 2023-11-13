package com.wanyun.sfseal.user.security.provider.wechat;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author WanYun
 */
public class WechatIdAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final Object wechatId;
    private Object authorizationCode;

    // ~ Constructors
    // ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>WechatIdAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     */
    public WechatIdAuthenticationToken(Object wechatId, Object authorizationCode) {
        super(null);
        this.wechatId = wechatId;
        this.authorizationCode = authorizationCode;
        setAuthenticated(false);
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param wechatId
     * @param authorizationCode
     * @param authorities
     */
    public WechatIdAuthenticationToken(Object wechatId, Object authorizationCode,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.wechatId = wechatId;
        this.authorizationCode = authorizationCode;
        super.setAuthenticated(true);
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public Object getCredentials() {
        return this.authorizationCode;
    }

    @Override
    public Object getPrincipal() {
        return this.wechatId;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        authorizationCode = null;
    }

}
