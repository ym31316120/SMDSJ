package com.ym.smdsj.controller;

import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.RequestResult;
import com.ym.smdsj.service.AccountService;
import com.ym.smdsj.support.factory.LogTaskFactory;
import com.ym.smdsj.support.manager.LogExeManager;
import com.ym.smdsj.util.IpUtil;
import com.ym.smdsj.util.RequestResponseUtil;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ym
 * @date 2019/3/11
 **/
@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);


    private AccountService accountService;

    @PostMapping("/register")
    public RequestResult register(HttpServletRequest request) {

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        AuthUser authUser = new AuthUser();
        String uid = params.get("uid");
        String password = params.get("password");
        String userKey = params.get("userKey");
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new RequestResult().error(1111, "账户信息缺失");
        }
        if (accountService.isAccountExistByUid(uid)) {
            // 账户已存在
            return new RequestResult().error(1111, "账户已存在");
        }

        authUser.setUid(uid);

//        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
//        authUser.setPassword(MD5Util.md5(realPassword + salt));
//        authUser.setSalt(salt);
        authUser.setPassword(password);

        if (!StringUtils.isEmpty(params.get("username"))) {
            authUser.setUsername(params.get("username"));
        }
        if (!StringUtils.isEmpty(params.get("realName"))) {
            authUser.setRealName(params.get("realName"));
        }
        if (!StringUtils.isEmpty(params.get("avatar"))) {
            authUser.setAvatar(params.get("avatar"));
        }
        if (!StringUtils.isEmpty(params.get("phone"))) {
            authUser.setPhone(params.get("phone"));
        }
        if (!StringUtils.isEmpty(params.get("email"))) {
            authUser.setEmail(params.get("email"));
        }
        if (!StringUtils.isEmpty(params.get("sex"))) {
            authUser.setSex(Byte.valueOf(params.get("sex")));
        }
        if (!StringUtils.isEmpty(params.get("createWhere"))) {
            authUser.setCreateWhere(Byte.valueOf(params.get("createWhere")));
        }
        authUser.setStatus((byte) 1);

        if (accountService.registerAccount(authUser)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (byte) 1, "注册成功"));
            return new RequestResult().success(200, "注册成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (byte) 0, "注册失败"));
            return new RequestResult().success(1111, "注册失败");
        }

    }


    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
