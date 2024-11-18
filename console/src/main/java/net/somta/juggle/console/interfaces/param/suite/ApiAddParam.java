package net.somta.juggle.console.interfaces.param.suite;

import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.core.enums.RequestTypeEnum;

import java.util.List;

/**
 * @author Gavin
 */
public class ApiAddParam {

    private Long suiteId;

    /**
     * api code,
     */
    private String apiCode;
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
    private RequestTypeEnum apiRequestType;

    /**
     * api请求内容类型 application/json
     */
    private String apiRequestContentType;

    private List<HeaderVO> apiHeaders;

    private List<InputParameterVO> apiInputParams;

    private List<OutputParameterVO> apiOutputParams;

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

    public RequestTypeEnum getApiRequestType() {
        return apiRequestType;
    }

    public void setApiRequestType(RequestTypeEnum apiRequestType) {
        this.apiRequestType = apiRequestType;
    }

    public String getApiRequestContentType() {
        return apiRequestContentType;
    }

    public void setApiRequestContentType(String apiRequestContentType) {
        this.apiRequestContentType = apiRequestContentType;
    }

    public List<HeaderVO> getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(List<HeaderVO> apiHeaders) {
        this.apiHeaders = apiHeaders;
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
}
