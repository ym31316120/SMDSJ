package com.ym.smdsj.service.impl;

import com.ym.smdsj.dao.AuthUserMapper;
import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ym
 * @date 2019/3/11
 **/

@Service("UserService")
public class UserServiceImpl implements UserService {

    private AuthUserMapper authUserMapper;


    @Override
    public List<AuthUser> getUserList() throws DataAccessException {
        return authUserMapper.selectUserList();
    }

    @Autowired
    public void setAuthUserMapper(AuthUserMapper authUserMapper) {
        this.authUserMapper = authUserMapper;
    }
}
