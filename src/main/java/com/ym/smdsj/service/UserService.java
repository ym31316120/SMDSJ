package com.ym.smdsj.service;

import com.github.pagehelper.PageInfo;
import com.ym.smdsj.domain.po.AuthUser;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author ym
 * @date 2019/3/11
 **/
public interface UserService {

    List<AuthUser> getUserList() throws DataAccessException;

    PageInfo<AuthUser> getUserListByPageInfo(Integer pageNum,Integer pageSize) throws DataAccessException;
}
