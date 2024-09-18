package net.somta.juggle.console.domain.suite.suiteinfo.vo;

import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;

import java.util.List;

/**
 * @author husong
 * @since 1.2.1
 */
public class SuiteMarketApiVO {

    private String apiCode;
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
    private List<HeaderVO> apiHeaders;

    /**
     * api入参
     */
    private List<InputParameterVO> apiInputParams;
    /**
     * api出参
     */
    private List<OutputParameterVO> apiOutputParams;

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
