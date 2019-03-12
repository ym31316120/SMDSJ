package com.ym.smdsj.domain.vo;

import java.util.Map;

/**
 * 请求结果实体
 *
 * @author ym
 * @date 2019/3/11
 */
public class RequestResult<T>{

    private int code;

    private String message;

    private boolean successResponse;

    private String requestId;

    private String locale;

    private String bid;

    private String siteId;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(boolean successResponse) {
        this.successResponse = successResponse;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RequestResult addData(T data) {
        this.setData(data);
        return this;
    }
    public RequestResult success(int statusCode,String statusMsg) {
        this.setSuccessResponse(Boolean.TRUE);
        this.setCode(statusCode);
        this.setMessage(statusMsg);
        return this;
    }
    public RequestResult error(int statusCode,String statusMsg) {
        this.setSuccessResponse(Boolean.FALSE);
        this.setCode(statusCode);
        this.setMessage(statusMsg);
        return this;
    }
}
