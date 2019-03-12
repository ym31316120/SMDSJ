package com.ym.smdsj.shiro.provider;

import com.ym.smdsj.domain.vo.Account;

/**
 * @author ym
 * @date 2019/3/12
 **/
public interface AccountProvider {
    Account loadAccount(String appId);
}
