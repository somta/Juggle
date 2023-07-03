package net.somta.juggle.console.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ApiDTO {

    private Integer id;

    private String domainName;

    private String apiUrl;

    private String apiName;

    private String apiDesc;

    private String apiRequestType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
