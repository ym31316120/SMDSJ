package com.ym.smdsj.controller;

import com.ym.smdsj.domain.po.AuthUser;
import com.ym.smdsj.domain.vo.RequestResult;
import com.ym.smdsj.service.AccountService;
import com.ym.smdsj.service.UserService;
import com.ym.smdsj.support.factory.LogTaskFactory;
import com.ym.smdsj.support.manager.LogExeManager;
import com.ym.smdsj.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ym
 * @date 2019/3/11
 **/
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private StringRedisTemplate redisTemplate;

    private AccountService accountService;

    private UserService userService;

    /**
     *这里已经在 passwordFilter 进行了登录认证
     * @Param [] 登录签发 JWT
     * @Return java.lang.String
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public RequestResult accountLogin(HttpServletRequest request) {
        Map<String, String> params = getRequestBody(request);
        String appId = params.get("appId");
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRole(appId);
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        String jwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), appId,
                "token-server", refreshPeriodTime >> 1, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, jwt, refreshPeriodTime, TimeUnit.SECONDS);
        AuthUser authUser = userService.getUserByAppId(appId);
        authUser.setPassword(null);
        authUser.setSalt(null);

        LogExeManager.getInstance().executeLogTask(LogTaskFactory.loginLog(appId, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (byte) 1, "登录成功"));

        Map<String,Object> map = new HashMap<>();
        map.put("jwt", jwt);
        map.put("user", authUser);

        return new RequestResult().success(1003, "issue jwt success").addData(map);
    }


    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public RequestResult register(HttpServletRequest request) {

        Map<String, String> params = getRequestBody(request);
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
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey);
        String realPassword = AESUtil.aesDecode(password, tokenKey);
        String salt = CommonUtil.getRandomString(6);
//         存储到数据库的密码为 MD5(原密码+盐值)
        authUser.setPassword(MD5Util.md5(realPassword + salt));
        authUser.setSalt(salt);
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

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
