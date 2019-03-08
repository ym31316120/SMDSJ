package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthAccountLog;

public interface AuthAccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthAccountLog record);

    int insertSelective(AuthAccountLog record);

    AuthAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthAccountLog record);

    int updateByPrimaryKey(AuthAccountLog record);
}