package com.ym.smdsj.shiro.realm;

import com.ym.smdsj.shiro.matcher.JwtMatcher;
import com.ym.smdsj.shiro.matcher.PasswordMatcher;
import com.ym.smdsj.shiro.provider.AccountProvider;
import com.ym.smdsj.shiro.token.PasswordToken;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * realm管理器
 * @author ym
 * @date 2019/3/12
 **/
@Component
public class RealmManager {
    private PasswordMatcher passwordMatcher;
    private JwtMatcher jwtMatcher;
    private AccountProvider accountProvider;
    @Autowired
    public RealmManager(AccountProvider accountProvider,PasswordMatcher passwordMatcher,JwtMatcher jwtMatcher) {
        this.accountProvider = accountProvider;
        this.passwordMatcher = passwordMatcher;
        this.jwtMatcher = jwtMatcher;
    }

    public List<Realm> initGetRealm() {
        List<Realm> realmList = new LinkedList<>();
        // ----- password
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setAccountProvider(accountProvider);
        passwordRealm.setCredentialsMatcher(passwordMatcher);
        passwordRealm.setAuthenticationTokenClass(PasswordToken.class);
        realmList.add(passwordRealm);
        // ----- jwt
//        JwtRealm jwtRealm = new JwtRealm();
//        jwtRealm.setCredentialsMatcher(jwtMatcher);
//        jwtRealm.setAuthenticationTokenClass(JwtToken.class);
//        realmList.add(jwtRealm);
        //返回一个不可修改的数组
        return Collections.unmodifiableList(realmList);
    }
}
