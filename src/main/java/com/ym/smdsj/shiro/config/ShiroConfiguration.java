package com.ym.smdsj.shiro.config;

import com.ym.smdsj.shiro.filter.ShiroFilterChainManager;
import com.ym.smdsj.shiro.realm.AModularRealmAuthenticator;
import com.ym.smdsj.shiro.realm.RealmManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro配置
 * @author ym
 * @date 2019/3/12
 **/
@Configuration
public class ShiroConfiguration {
    /**
     * 配置shiro关键的过滤工厂类
     * @param securityManager
     * @param filterChainManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {
        RestShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());
        return shiroFilterFactoryBean;
    }

    /**
     * 配置上面方法用到的核心安全事务管理器
     * @param realmManager 入参是权限登录器的管理工具类，里面包含了各种自定义的权限控制器
     * @return
     */
    @Bean
    public SecurityManager securityManager(RealmManager realmManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(new AModularRealmAuthenticator());
        securityManager.setRealms(realmManager.initGetRealm());
//
//        // 无状态subjectFactory设置
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator)((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(Boolean.FALSE);
//        StatelessWebSubjectFactory subjectFactory = new StatelessWebSubjectFactory();
//        securityManager.setSubjectFactory(subjectFactory);
//
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }


}
