package net.somta.juggle.core.http;

import net.somta.juggle.core.enums.RequestTypeEnum;

import java.util.Map;

/**
 * 请求实体
 * @author husong
 */
public class Request {

    /**
     * 请求url,带域名的完整地址
     */
    private String requestUrl;
    /**
     * 请求方法类型 GET POST PUT DELETE
     */
    private RequestTypeEnum requestType;

    /**
     * 请求头
     */
    private Map<String,String> requestHeaders;

    /**
     * 请求参数
     */
    private Map<String,Object> requestParams;

    /**
     * 请求超时时间 单位：毫秒
     */
    private Integer timeout;


    public Request(RequestTypeEnum requestType, String requestUrl) {
        if (requestUrl == null) {
            throw new IllegalArgumentException("reqMethod,reqHeaders,reqBody should not be null");
        }
        this.requestType = requestType;
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public RequestTypeEnum getRequestType() {
        return requestType;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}