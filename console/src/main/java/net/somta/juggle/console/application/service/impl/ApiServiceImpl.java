package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.interfaces.param.*;
import net.somta.juggle.console.infrastructure.mapper.ApiMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.model.Api;
import net.somta.juggle.console.infrastructure.model.Parameter;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.application.service.IApiService;
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

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiUpdateParam.getId()));
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
