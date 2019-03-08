package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthRoleResource;

public interface AuthRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    AuthRoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleResource record);

    int updateByPrimaryKey(AuthRoleResource record);
}