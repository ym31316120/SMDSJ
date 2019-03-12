package com.ym.smdsj.support.factory;

import com.ym.smdsj.domain.po.AuthAccountLog;
import com.ym.smdsj.domain.po.AuthOperationLog;

import java.util.Date;

/**
 * @author ym
 * @date 2019/3/11
 **/
public class LogFactory {
    private LogFactory() {

    }

    public static AuthAccountLog createAccountLog(String userId, String logName, String ip, byte succeed, String message) {
        AuthAccountLog accountLog = new AuthAccountLog();
        accountLog.setUserId(userId);
        accountLog.setLogName(logName);
        accountLog.setIp(ip);
        accountLog.setSucceed(succeed);
        accountLog.setMessage(message);
        accountLog.setCreateTime(new Date());
        return accountLog;
    }

    public static AuthOperationLog createOperationLog(String userId,String logName,String api, String method, byte succeed, String message) {
        AuthOperationLog operationLog = new AuthOperationLog();
        operationLog.setUserId(userId);
        operationLog.setLogName(logName);
        operationLog.setApi(api);
        operationLog.setMethod(method);
        operationLog.setSucceed(succeed);
        operationLog.setMessage(message);
        operationLog.setCreateTime(new Date());
        return operationLog;
    }
}
