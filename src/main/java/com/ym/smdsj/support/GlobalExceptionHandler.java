package com.ym.smdsj.support;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.ym.smdsj.domain.vo.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ym
 * @date 2019/3/12
 **/
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /* *
     * @Description 拦截操作数据库异常
     * @Param [e]
     * @Return com.usthe.bootshiro.domain.vo.Message
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.OK)
    public RequestResult sqlException(DataAccessException e) {
        LOGGER.error("数据操作异常:",e);
        final Throwable cause = e.getCause();
        // 之后判断cause类型进一步记录日志处理
        if (cause instanceof MySQLIntegrityConstraintViolationException) {
            return new RequestResult().error(1111, "数据冲突操作失败");
        }
        return new RequestResult().error(1111, "服务器开小差");
    }

    /* *
     * @Description拦截未知的运行时异常
     * @Param [e]
     * @Return com.usthe.bootshiro.domain.vo.Message
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public RequestResult notFoundException(RuntimeException e) {
        LOGGER.error("运行时异常:",e);
        return new RequestResult().error(1111,"服务器开小差");
    }

}
