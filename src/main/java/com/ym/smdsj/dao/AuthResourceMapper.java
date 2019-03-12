package com.ym.smdsj.dao;

import com.ym.smdsj.domain.po.AuthResource;
import com.ym.smdsj.shiro.rule.RolePermRule;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthResourceMapper {
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(AuthResource record) throws DataAccessException;

    int insertSelective(AuthResource record) throws DataAccessException;

    AuthResource selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(AuthResource record) throws DataAccessException;

    int updateByPrimaryKey(AuthResource record) throws DataAccessException;

    List<RolePermRule> selectRoleRules()  throws DataAccessException;
}