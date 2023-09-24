package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.repository.IApiRepository;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.*;
import net.somta.juggle.console.infrastructure.mapper.ApiMapper;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.application.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl extends BaseServiceImpl<ApiPO> implements IApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Autowired
    private IApiRepository apiRepository;



    @Override
    public IBaseMapper getMapper() {
        return apiMapper;
    }

    @Override
    public ResponseDataResult<Boolean> addApi(ApiAddParam apiAddParam) {
        ApiAO apiAO = new ApiAO();
        apiAO.setDomainId(apiAddParam.getDomainId());
        apiAO.setApiUrl(apiAddParam.getApiUrl());
        apiAO.setApiName(apiAddParam.getApiName());
        apiAO.setApiDesc(apiAddParam.getApiDesc());
        apiAO.setApiRequestType(apiAddParam.getApiRequestType());
        apiAO.setApiRequestContentType(apiAddParam.getApiRequestContentType());

        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameter(apiAddParam.getApiInputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode())
                       .setOutputParameter(apiAddParam.getApiOutputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode());
        apiAO.setParameterEntity(parameterEntity);
        apiRepository.addApi(apiAO);
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public ResponseDataResult<Boolean> deleteApi(Long apiId) {
        apiRepository.deleteApi(apiId);
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public ResponseDataResult<Boolean> updateApi(ApiUpdateParam apiUpdateParam) {
        ApiAO apiAO = new ApiAO();
        apiAO.setId(apiUpdateParam.getId());
        apiAO.setDomainId(apiUpdateParam.getDomainId());
        apiAO.setApiUrl(apiUpdateParam.getApiUrl());
        apiAO.setApiName(apiUpdateParam.getApiName());
        apiAO.setApiDesc(apiUpdateParam.getApiDesc());
        apiAO.setApiRequestType(apiUpdateParam.getApiRequestType());
        apiAO.setApiRequestContentType(apiUpdateParam.getApiRequestContentType());

        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameter(apiUpdateParam.getApiInputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode())
                .setOutputParameter(apiUpdateParam.getApiOutputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode());
        apiAO.setParameterEntity(parameterEntity);
        apiRepository.updateApi(apiAO);
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public ApiInfoDTO queryApiInfo(Long apiId) {
        ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
        ApiAO apiAO = apiRepository.queryApi(apiId);
        apiInfoDTO.setId(apiAO.getId());
        apiInfoDTO.setDomainId(apiAO.getDomainId());
        apiInfoDTO.setApiUrl(apiAO.getApiUrl());
        apiInfoDTO.setApiName(apiAO.getApiName());
        apiInfoDTO.setApiDesc(apiAO.getApiDesc());
        apiInfoDTO.setApiRequestType(apiAO.getApiRequestType());
        apiInfoDTO.setApiRequestContentType(apiAO.getApiRequestContentType());
        apiInfoDTO.setApiInputParams(apiAO.getParameterEntity().getInputParameter());
        apiInfoDTO.setApiOutputParams(apiAO.getParameterEntity().getOutputParameter());
        return apiInfoDTO;
    }

    @Override
    public List<ApiDTO> getApiListByDomainId(Long domainId) {
        List<ApiDTO> apiDTOList = new ArrayList<>();
        // todo 这里逻辑没写完，要转换到dto上
        apiMapper.queryApiListByDomainId(domainId);
        return apiDTOList;
    }

    /*@Override
    public List<Api> getApiList() {
        return apiMapper.queryApiList();
    }*/

    @Override
    public ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam) {
        List<ApiDTO> apiList = null;
        Long total =  apiMapper.queryApiCount(apiQueryParam);
        if(total > 0){
            apiList = apiMapper.queryApiPageList(apiQueryParam);
        }
        return ResponsePaginationDataResult.setPaginationDataResult(total,apiList);
    }

}
