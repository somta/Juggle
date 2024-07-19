package net.somta.juggle.console.domain.suite.suiteinfo.vo;

/**
 * @author husong
 * @since 1.2.1
 */
public class SuiteMarketApiVO {
    private Long id;

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

    /**
     * api请求头
     */
    private String apiHeaders;

    /**
     * api入参
     */
    private String apiInputParameter;
    /**
     * api出参
     */
    private String apiOutputParameter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public String getApiInputParameter() {
        return apiInputParameter;
    }

    public void setApiInputParameter(String apiInputParameter) {
        this.apiInputParameter = apiInputParameter;
    }

    public String getApiOutputParameter() {
        return apiOutputParameter;
    }

    public void setApiOutputParameter(String apiOutputParameter) {
        this.apiOutputParameter = apiOutputParameter;
    }
}
