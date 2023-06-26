package net.somta.juggle.console.model.param;

import java.util.List;

public class ApiUpdateParam {
    private Long id;

    private Long domainId;

    /**
     * api接口地址
     */
    private String apiUrl;

    /**
     * api接口名称
     */
    private String apiName;

    /**
     * api描述
     */
    private String apiDesc;

    /**
     * api请求类型  GET POST PUT
     */
    private String apiRequestType;

    /**
     * api请求内容类型 application/json
     */
    private String apiRequestContentType;

    private List<InputParameterParam> apiInputParams;

    private List<OutputParameterParam> apiOutputParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    public String getApiRequestType() {
        return apiRequestType;
    }

    public void setApiRequestType(String apiRequestType) {
        this.apiRequestType = apiRequestType;
    }

    public String getApiRequestContentType() {
        return apiRequestContentType;
    }

    public void setApiRequestContentType(String apiRequestContentType) {
        this.apiRequestContentType = apiRequestContentType;
    }

    public List<InputParameterParam> getApiInputParams() {
        return apiInputParams;
    }

    public void setApiInputParams(List<InputParameterParam> apiInputParams) {
        this.apiInputParams = apiInputParams;
    }

    public List<OutputParameterParam> getApiOutputParams() {
        return apiOutputParams;
    }

    public void setApiOutputParams(List<OutputParameterParam> apiOutputParams) {
        this.apiOutputParams = apiOutputParams;
    }
}
