package com.ym.smdsj.shiro.provider;

import com.ym.smdsj.shiro.rule.RolePermRule;

import java.util.List;

/**
 * @author ym
 * @date 2019/3/12
 **/
public interface ShiroFilterRulesProvider {
    /* *
     * @Description
     * 加载基于角色/资源的过滤规则
     * 即：用户-角色-资源（URL），对应关系存储与数据库中
     * 在shiro中生成的过滤器链为：url=jwt[角色1、角色2、角色n]
     * @Param []
     * @Return java.util.List
     */
    public List<RolePermRule> loadRolePermRules();
}
