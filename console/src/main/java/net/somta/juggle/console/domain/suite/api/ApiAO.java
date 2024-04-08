package net.somta.juggle.console.domain.suite.api;

import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.converter.IParameterConverter;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.core.enums.RequestTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gavin
 */
public class ApiAO {

    private Long id;

    private Long suiteId;

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
    private RequestTypeEnum apiRequestType;

    /**
     * api请求内容类型 application/json
     */
    private String apiRequestContentType;

    private List<HeaderVO> apiHeaders;

    private ParameterEntity parameterEntity;

    /**
     * 初始化参数实体
     * @param apiInputParamList
     * @param apiOutputParamList
     */
    public void initParameterList(List<InputParameterVO> apiInputParamList, List<OutputParameterVO> apiOutputParamList) {
        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameterList(apiInputParamList);
        parameterEntity.setOutputParameterList(apiOutputParamList);
        this.parameterEntity = parameterEntity;
    }

    public void initHeaderList(List<HeaderVO> apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public void parseHeader(List<ParameterPO> parameterPoList) {
        List<ParameterPO> headerPoList = parameterPoList.stream()
                .filter(parameter -> ParameterTypeEnum.HEADER.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        this.apiHeaders = IParameterConverter.IMPL.headerParamerterPoListToVoList(headerPoList);
    }

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

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }



}
