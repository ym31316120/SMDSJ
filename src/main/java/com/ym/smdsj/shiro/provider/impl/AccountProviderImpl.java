package com.ym.smdsj.shiro.provider.impl;

import com.ym.smdsj.domain.vo.Account;
import com.ym.smdsj.service.AccountService;
import com.ym.smdsj.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author ym
 * @date 2019/3/12
 **/
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {


    private AccountService accountService;

    @Override
    public Account loadAccount(String appId) {
        return accountService.loadAccount(appId);
    }

    @Autowired
    @Qualifier("AccountService")
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
