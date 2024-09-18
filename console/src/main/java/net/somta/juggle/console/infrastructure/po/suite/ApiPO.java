package net.somta.juggle.console.infrastructure.po.suite;

import net.somta.core.base.BaseModel;

/**
 * @author Gavin
 */
public class ApiPO extends BaseModel {

    private Long id;

    private Long suiteId;

    private String apiCode;
    /**
     * http api request address
     */
    private String apiUrl;

    /**
     * api name
     */
    private String apiName;

    /**
     * api desc
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

    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Long suiteId) {
        this.suiteId = suiteId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
