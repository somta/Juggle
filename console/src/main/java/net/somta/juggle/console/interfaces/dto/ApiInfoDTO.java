package net.somta.juggle.console.interfaces.dto;

import net.somta.juggle.console.domain.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;

import java.util.List;

/**
 * @author Gavin
 */
public class ApiInfoDTO {

    private Long id;

    private Long domainId;

    private String apiUrl;

    private String apiName;

    private String apiDesc;

    private String apiRequestType;

    private String apiRequestContentType;

    private List<HeaderVO> apiHeaders;

    private List<InputParameterVO> apiInputParams;

    private List<OutputParameterVO> apiOutputParams;

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

    public List<HeaderVO> getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(List<HeaderVO> apiHeaders) {
        this.apiHeaders = apiHeaders;
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

    public List<InputParameterVO> getApiInputParams() {
        return apiInputParams;
    }

    public void setApiInputParams(List<InputParameterVO> apiInputParams) {
        this.apiInputParams = apiInputParams;
    }

    public List<OutputParameterVO> getApiOutputParams() {
        return apiOutputParams;
    }

    public void setApiOutputParams(List<OutputParameterVO> apiOutputParams) {
        this.apiOutputParams = apiOutputParams;
    }

    @Override
    public String toString() {
        return "ApiInfoDTO{" +
                "id=" + id +
                ", domainId=" + domainId +
                ", apiUrl='" + apiUrl + '\'' +
                ", apiName='" + apiName + '\'' +
                ", apiDesc='" + apiDesc + '\'' +
                ", apiRequestType='" + apiRequestType + '\'' +
                ", apiRequestContentType='" + apiRequestContentType + '\'' +
                ", apiInputParams=" + apiInputParams +
                ", apiOutputParams=" + apiOutputParams +
                '}';
    }
}
