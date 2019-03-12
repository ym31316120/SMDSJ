package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthUser;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserMapper {
    int deleteByPrimaryKey(String uid) throws DataAccessException;

    int insert(AuthUser record) throws DataAccessException;

    int insertSelective(AuthUser record) throws DataAccessException;

    AuthUser selectByPrimaryKey(String uid) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthUser record) throws DataAccessException;

    int updateByPrimaryKey(AuthUser record) throws DataAccessException;

    //上面的接口方法都是generator自动生成的

    List<AuthUser> selectUserList() throws DataAccessException;

    AuthUser selectByUniqueKey(String appId) throws DataAccessException;
}