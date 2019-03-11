package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(AuthUser record);

    int updateByPrimaryKey(AuthUser record);

    //上面的接口方法都是generator自动生成的

    List<AuthUser> selectUserList();
}