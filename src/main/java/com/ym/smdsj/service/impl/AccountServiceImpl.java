package com.ym.smdsj.service.impl;

import com.ym.smdsj.dao.AuthUserMapper;
import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @author ym
 * @date 2019/3/11
 **/
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    private AuthUserMapper userMapper;


    @Override
    public boolean isAccountExistByUid(String uid) {
        AuthUser user = userMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) {
        Date nowDate = new Date(System.currentTimeMillis());
        account.setCreateTime(nowDate);
        account.setUpdateTime(nowDate);
        return userMapper.insertSelective(account) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Autowired
    public void setUserMapper(AuthUserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
