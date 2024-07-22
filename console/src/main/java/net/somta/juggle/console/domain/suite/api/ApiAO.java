package net.somta.juggle.console.domain.suite.api;

import net.somta.common.utils.MapUtil;
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
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.Property;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
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
                DataType dataType = outputParameter.getDataType();
                if(DataTypeEnum.Object.equals(dataType.getType())){
                    parseObjectAndFill(outputParameter.getParamKey(),dataType,result,originalResult);
                } else if (DataTypeEnum.List.equals(dataType.getType())) {
                    //todo 待完善
                } else {
                    result.put(outputParameter.getParamKey(),originalResult.get(outputParameter.getParamKey()));
                }
            }
        }
        System.out.println(result);
        //todo 待完善，要根据出参和原始map，封装一个新的响应map
        return originalResult;
    }

    /**
     * 解析对象属性并填充对象的值到结果中
     * @param outputParameterKey
     * @param result
     * @param originalResult
     */
    private void parseObjectAndFill(String outputParameterKey, DataType outputParamDataType,Map<String, Object> result,Map<String, Object> originalResult) {
        List<Property> objectProperty = outputParamDataType.getObjectStructure();
        for (Property property : objectProperty){
            if(DataTypeEnum.Object.equals(property.getDataType().getType())){
                parseObjectAndFill(property.getPropKey(),property.getDataType(),result,originalResult);
            } else if (DataTypeEnum.List.equals(property.getDataType().getType())) {
                // todo 待完善
            } else {
                result.put(outputParameterKey,null);
            }
            originalResult.get(property.getPropKey());
        }

        result.put(outputParameterKey,null);
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
