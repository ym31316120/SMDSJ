package com.ym.smdsj.shiro.provider.impl;

import com.ym.smdsj.dao.AuthResourceMapper;
import com.ym.smdsj.shiro.provider.ShiroFilterRulesProvider;
import com.ym.smdsj.shiro.rule.RolePermRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ym
 * @date 2019/3/12
 **/
@Service("ShiroFilterRulesProvider")
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    private AuthResourceMapper authResourceMapper;

    @Override
    public List<RolePermRule> loadRolePermRules() {

        return authResourceMapper.selectRoleRules();
    }

    @Autowired
    public void setAuthResourceMapper(AuthResourceMapper authResourceMapper) {
        this.authResourceMapper = authResourceMapper;
    }
}
