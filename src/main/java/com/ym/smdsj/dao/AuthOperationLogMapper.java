package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthOperationLog;

public interface AuthOperationLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthOperationLog record);

    int insertSelective(AuthOperationLog record);

    AuthOperationLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthOperationLog record);

    int updateByPrimaryKey(AuthOperationLog record);
}