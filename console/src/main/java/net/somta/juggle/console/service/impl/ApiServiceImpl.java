package net.somta.juggle.console.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.enums.ParameterTypeEnum;
import net.somta.juggle.console.mapper.ApiMapper;
import net.somta.juggle.console.mapper.ParameterMapper;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.Parameter;
import net.somta.juggle.console.model.VariableInfo;
import net.somta.juggle.console.model.dto.ApiDTO;
import net.somta.juggle.console.model.param.*;
import net.somta.juggle.console.model.vo.ParameterVO;
import net.somta.juggle.console.service.IApiService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl extends BaseServiceImpl<Api> implements IApiService {

    @Autowired
    private ApiMapper apiMapper;
    @Autowired
    private ParameterMapper parameterMapper;

    @Override
    public IBaseMapper getMapper() {
        return apiMapper;
    }

    @Transactional
    @Override
    public ResponseDataResult<Boolean> addApi(ApiAddParam apiAddParam) {
        Api api = new Api();
        api.setDomainId(apiAddParam.getDomainId());
        api.setApiUrl(apiAddParam.getApiUrl());
        api.setApiName(apiAddParam.getApiName());
        api.setApiDesc(apiAddParam.getApiDesc());
        api.setApiRequestType(apiAddParam.getApiRequestType());
        api.setApiRequestContentType(apiAddParam.getApiRequestContentType());
        apiMapper.addApi(api);
        saveParameters(api.getId(),apiAddParam.getApiInputParams(), apiAddParam.getApiOutputParams());
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public ResponseDataResult<Boolean> updateApi(ApiUpdateParam apiUpdateParam) {
        Api api = new Api();
        api.setId(apiUpdateParam.getId());
        api.setDomainId(apiUpdateParam.getDomainId());
        api.setApiUrl(apiUpdateParam.getApiUrl());
        api.setApiName(apiUpdateParam.getApiName());
        api.setApiDesc(apiUpdateParam.getApiDesc());
        api.setApiRequestType(apiUpdateParam.getApiRequestType());
        api.setApiRequestContentType(apiUpdateParam.getApiRequestContentType());
        apiMapper.update(api);

        ParameterVO parameterVO = new ParameterVO();
        parameterVO.setSourceType(ParameterSourceTypeEnum.API.getCode());
        parameterVO.setSourceId(apiUpdateParam.getId());
        parameterMapper.deleteParameter(parameterVO);
        saveParameters(apiUpdateParam.getId(),apiUpdateParam.getApiInputParams(), apiUpdateParam.getApiOutputParams());
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public List<Api> getApiListByDomainId(Long domainId) {
        return apiMapper.queryApiListByDomainId(domainId);
    }

    @Override
    public List<Api> getApiList() {
        return apiMapper.queryApiList();
    }

    @Override
    public ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam) {
        List<ApiDTO> apiList = null;
        Long total =  apiMapper.queryApiCount(apiQueryParam);
        if(total > 0){
            apiList = apiMapper.queryApiPageList(apiQueryParam);
        }
        return ResponsePaginationDataResult.setPaginationDataResult(total,apiList);
    }

    private void saveParameters(Long apiId,List<InputParameterParam> apiInputParams,
                                List<OutputParameterParam> apiOutputParams){
        List<Parameter> parameters = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(apiInputParams)){
            for (InputParameterParam apiInputParam : apiInputParams) {
                Parameter parameter = new Parameter();
                parameter.setParamKey(apiInputParam.getParamKey());
                parameter.setParamType(ParameterTypeEnum.INPUT_PARAM.getCode());
                parameter.setParamName(apiInputParam.getParamName());
                parameter.setDataType(apiInputParam.getDataType());
                parameter.setRequired(apiInputParam.getRequired());
                parameter.setSourceType(ParameterSourceTypeEnum.API.getCode());
                parameter.setSourceId(apiId);
                parameters.add(parameter);
            }
        }
        if(CollectionUtils.isNotEmpty(apiOutputParams)){
            for (OutputParameterParam apiOutputParam : apiOutputParams) {
                Parameter parameter = new Parameter();
                parameter.setParamKey(apiOutputParam.getParamKey());
                parameter.setParamType(ParameterTypeEnum.OUTPUT_PARAM.getCode());
                parameter.setParamName(apiOutputParam.getParamName());
                parameter.setDataType(apiOutputParam.getDataType());
                parameter.setSourceType(ParameterSourceTypeEnum.API.getCode());
                parameter.setSourceId(apiId);
                parameters.add(parameter);
            }
        }
        if(CollectionUtils.isNotEmpty(parameters)){
            parameterMapper.batchAddParameter(parameters);
        }
    }

}
