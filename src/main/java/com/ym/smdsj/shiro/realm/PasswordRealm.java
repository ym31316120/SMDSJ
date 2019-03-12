package com.ym.smdsj.shiro.realm;

import com.ym.smdsj.domain.vo.Account;
import com.ym.smdsj.shiro.provider.AccountProvider;
import com.ym.smdsj.shiro.token.PasswordToken;
import com.ym.smdsj.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 登录认证realm
 * @author ym
 * @date 2019/3/12
 **/
public class PasswordRealm extends AuthorizingRealm {
    private AccountProvider accountProvider;


    //此Realm只支持PasswordToken
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return PasswordToken.class;
    }
//
//
    // 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof PasswordToken)){
            return null;
        }

        if(null==authenticationToken.getPrincipal()||null==authenticationToken.getCredentials()){
            throw new UnknownAccountException();
        }
        String appId = (String)authenticationToken.getPrincipal();
        Account account = accountProvider.loadAccount(appId);
        if (account != null) {
            // 用盐对密码进行MD5加密
            ((PasswordToken) authenticationToken).setPassword(MD5Util.md5(((PasswordToken) authenticationToken).getPassword()+account.getSalt()));
            return new SimpleAuthenticationInfo(appId,account.getPassword(),getName());
        } else {
            return new SimpleAuthenticationInfo(appId,"",getName());
        }

    }

    public void setAccountProvider(AccountProvider accountProvider) {
        this.accountProvider = accountProvider;
    }
}
