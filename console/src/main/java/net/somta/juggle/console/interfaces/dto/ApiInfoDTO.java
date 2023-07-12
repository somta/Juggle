package net.somta.juggle.console.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.somta.juggle.console.infrastructure.model.Parameter;
import net.somta.juggle.console.interfaces.param.InputParameterParam;
import net.somta.juggle.console.interfaces.param.OutputParameterParam;

import java.util.Date;
import java.util.List;

public class ApiInfoDTO {

    private Long id;

    private Long domainId;

    private String apiUrl;

    private String apiName;

    private String apiDesc;

    private String apiRequestType;

    private List<Parameter> apiInputParams;

    private List<Parameter> apiOutputParams;

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

    public List<Parameter> getApiInputParams() {
        return apiInputParams;
    }

    public void setApiInputParams(List<Parameter> apiInputParams) {
        this.apiInputParams = apiInputParams;
    }

    public List<Parameter> getApiOutputParams() {
        return apiOutputParams;
    }

    public void setApiOutputParams(List<Parameter> apiOutputParams) {
        this.apiOutputParams = apiOutputParams;
    }
}
