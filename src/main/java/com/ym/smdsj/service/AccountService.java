package com.ym.smdsj.service;

import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.Account;

/**
 * @author ym
 * @date 2019/3/11
 **/
public interface AccountService {
    boolean isAccountExistByUid(String uid);
    boolean registerAccount(AuthUser account);
    Account loadAccount(String appId);
}
