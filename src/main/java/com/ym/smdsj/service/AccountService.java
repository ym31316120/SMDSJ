package com.ym.smdsj.service;

import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.Account;
import org.springframework.dao.DataAccessException;

/**
 * @author ym
 * @date 2019/3/11
 **/
public interface AccountService {
    boolean isAccountExistByUid(String uid) throws DataAccessException;
    boolean registerAccount(AuthUser account) throws DataAccessException;
    Account loadAccount(String appId) throws DataAccessException;
    String loadAccountRole(String appId) throws DataAccessException;
}
