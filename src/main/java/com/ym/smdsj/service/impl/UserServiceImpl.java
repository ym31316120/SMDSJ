package com.ym.smdsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ym.smdsj.dao.AuthUserMapper;
import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    @Override
    public PageInfo<AuthUser> getUserListByPageInfo(Integer pageNum,Integer pageSize) throws DataAccessException {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthUser> authUsers = this.getUserList();
        return new PageInfo<>(authUsers);
    }

    @Override
    public AuthUser getUserByAppId(String appId) {
        return authUserMapper.selectByUniqueKey(appId);
    }

    @Autowired
    public void setAuthUserMapper(AuthUserMapper authUserMapper) {
        this.authUserMapper = authUserMapper;
    }
}
