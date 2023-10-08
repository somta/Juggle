package net.somta.juggle.console.application.service.impl;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.IApiAssembler;
import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.repository.IApiRepository;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.*;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.application.service.IApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements IApiService {

    private final IApiRepository apiRepository;

    public ApiServiceImpl(IApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public ResponseDataResult<Boolean> addApi(ApiAddParam apiAddParam) {
        ApiAO apiAO = IApiAssembler.IMPL.paramToAo(apiAddParam);

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
        ApiAO apiAO = IApiAssembler.IMPL.paramToAo(apiUpdateParam);

        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.setInputParameter(apiUpdateParam.getApiInputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode())
                .setOutputParameter(apiUpdateParam.getApiOutputParams(),apiAO.getId(),ParameterSourceTypeEnum.API.getCode());
        apiAO.setParameterEntity(parameterEntity);
        apiRepository.updateApi(apiAO);
        return ResponseDataResult.setResponseResult();
    }

    @Override
    public ApiInfoDTO queryApiInfo(Long apiId) {
        ApiAO apiAO = apiRepository.queryApi(apiId);
        ApiInfoDTO apiInfoDTO = IApiAssembler.IMPL.aoToDto(apiAO);
        return apiInfoDTO;
    }

    @Override
    public List<ApiDTO> getApiListByDomainId(Long domainId) {
        List<ApiVO> apiVoList = apiRepository.getApiListByDomainId(domainId);
        List<ApiDTO> apiDTOList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        return apiDTOList;
    }

    @Override
    public ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam) {
        List<ApiDTO> apiList = null;
        Long total =  apiRepository.queryApiCount(apiQueryParam);
        if(total > 0){
            List<ApiVO> apiVoList = apiRepository.queryApiPageList(apiQueryParam);
            apiList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        }
        return ResponsePaginationDataResult.setPaginationDataResult(total,apiList);
    }

}
