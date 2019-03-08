package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthUserRole;

public interface AuthUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthUserRole record);

    int insertSelective(AuthUserRole record);

    AuthUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthUserRole record);

    int updateByPrimaryKey(AuthUserRole record);
}