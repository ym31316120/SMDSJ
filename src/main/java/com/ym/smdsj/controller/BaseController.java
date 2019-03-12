package com.ym.smdsj.controller;

import com.ym.smdsj.util.RequestResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ym
 * @date 2019/3/12
 **/
public abstract class BaseController {
    @SuppressWarnings("rawtypes")
    protected Map<String, String> getRequestParameter(HttpServletRequest request) {

        return RequestResponseUtil.getRequestParameters(request);
    }

    protected Map<String,String> getRequestBody(HttpServletRequest request) {
        return RequestResponseUtil.getRequestBodyMap(request);
    }

    protected Map<String, String > getRequestHeader(HttpServletRequest request) {
        return RequestResponseUtil.getRequestHeaders(request);
    }
}
