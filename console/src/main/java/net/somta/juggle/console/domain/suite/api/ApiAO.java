package net.somta.juggle.console.domain.suite.api;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.converter.IParameterConverter;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.enums.RequestTypeEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Property;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     *
     * @param originalResult original API response result data
     * @return
     */
    public Map<String, Object> handleApiResponseResult(Map<String, Object> originalResult) {
        Map<String, Object> result = new HashMap<>();
        List<OutputParameterVO> outputParameterList = this.parameterEntity.getOutputParameterList();
        if(CollectionUtils.isNotEmpty(outputParameterList)){
            for (OutputParameterVO outputParameter : outputParameterList){
                DataType dataType = JsonSerializeHelper.deserialize(outputParameter.getDataType(), DataType.class);
                if(DataTypeEnum.Object.equals(dataType.getType())){
                    parseObjectAndFill(outputParameter.getParamKey(),dataType,result,originalResult);
                } else {
                    result.put(outputParameter.getParamKey(),originalResult.get(outputParameter.getParamKey()));
                }
            }
        }
        //todo 待完善，要根据出参和原始map，封装一个新的响应map
        return originalResult;
    }

    /**
     * 解析对象属性并填充对象的值到结果中
     * @param outputParameter
     * @param result
     * @param originalResult
     */
    private void parseObjectAndFill(String outputParameter, DataType outputParamDataType,Map<String, Object> result,Map<String, Object> originalResult) {
        Property objectProperty = JsonSerializeHelper.deserialize(outputParamDataType.getObjectStructure(), Property.class);

        result.put(outputParameter,null);
    }

    private Map<String,Object> getObjectRealData(){
        return null;
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
