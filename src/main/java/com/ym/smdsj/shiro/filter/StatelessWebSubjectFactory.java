package com.ym.smdsj.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 对于无状态的TOKEN不创建session 这里都不使用session
 * @author ym
 * @date 2019/3/12
 **/
public class StatelessWebSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 这里都不创建session
        AuthenticationToken token = context.getAuthenticationToken();
        context.setSessionCreationEnabled(Boolean.FALSE);
        return super.createSubject(context);
    }

    public StatelessWebSubjectFactory() {}

}
