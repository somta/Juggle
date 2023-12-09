package net.somta.juggle.console.infrastructure.repository;

import net.somta.core.exception.BizException;
import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.enums.ApiErrorEnum;
import net.somta.juggle.console.domain.api.repository.IApiRepository;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.converter.IApiConverter;
import net.somta.juggle.console.infrastructure.converter.IObjConverter;
import net.somta.juggle.console.infrastructure.mapper.ApiMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gavin
 */
@Repository
public class ApiRepositoryImpl implements IApiRepository {

    private final ApiMapper apiMapper;
    private final ParameterMapper parameterMapper;

    public ApiRepositoryImpl(ApiMapper apiMapper, ParameterMapper parameterMapper) {
        this.apiMapper = apiMapper;
        this.parameterMapper = parameterMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addApi(ApiAO apiAo) {
        ApiPO apiPo = new ApiPO();
        apiPo.setDomainId(apiAo.getDomainId());
        apiPo.setApiUrl(apiAo.getApiUrl());
        apiPo.setApiName(apiAo.getApiName());
        apiPo.setApiDesc(apiAo.getApiDesc());
        apiPo.setApiRequestType(apiAo.getApiRequestType().name());
        apiPo.setApiRequestContentType(apiAo.getApiRequestContentType());
        apiPo.setCreatedAt(new Date());
        apiMapper.addApi(apiPo);

        List<ParameterPO> parameterPoList = new ArrayList<>();
        List<ParameterPO> parameterList = apiAo.getParameterEntity().getParameterPoList(apiPo.getId(),ParameterSourceTypeEnum.API.getCode());
        parameterPoList.addAll(parameterList);

        List<ParameterPO> headerList = IApiConverter.IMPL.headerListToParameterList(apiPo.getId(),apiAo.getHeaderList());
        parameterPoList.addAll(headerList);
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterMapper.batchAddParameter(headerList);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteApi(Long apiId) {
        apiMapper.deleteById(apiId);
        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiId));
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateApi(ApiAO apiAo) {
        ApiPO apiPo = new ApiPO();
        apiPo.setId(apiAo.getId());
        apiPo.setDomainId(apiAo.getDomainId());
        apiPo.setApiUrl(apiAo.getApiUrl());
        apiPo.setApiName(apiAo.getApiName());
        apiPo.setApiDesc(apiAo.getApiDesc());
        apiPo.setApiRequestType(apiAo.getApiRequestType().name());
        apiPo.setApiRequestContentType(apiAo.getApiRequestContentType());
        apiMapper.update(apiPo);

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiAo.getId()));
        List<ParameterPO> parameterPoList = new ArrayList<>();
        List<ParameterPO> parameterList = apiAo.getParameterEntity().getParameterPoList(apiAo.getId(),ParameterSourceTypeEnum.API.getCode());
        parameterPoList.addAll(parameterList);

        List<ParameterPO> headerList = IApiConverter.IMPL.headerListToParameterList(apiAo.getId(),apiAo.getHeaderList());
        parameterPoList.addAll(headerList);
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterMapper.batchAddParameter(headerList);
        }
        return true;
    }

    @Override
    public ApiAO queryApi(Long apiId) {
        ApiPO apiPo = apiMapper.queryById(apiId);
        if(apiPo == null){
            throw new BizException(ApiErrorEnum.API_NOT_EXIST);
        }
        ApiAO apiAo = IApiConverter.IMPL.poToAo(apiPo);

        ParameterEntity parameterEntity = new ParameterEntity();
        List<ParameterPO> parameters = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiId));
        parameterEntity.parseParameter(parameters);
        apiAo.setParameterEntity(parameterEntity);
        return apiAo;
    }

    @Override
    public List<ApiVO> getApiListByDomainId(Long domainId) {
        List<ApiPO> apiList = apiMapper.queryApiListByDomainId(domainId);
        List<ApiVO> apis = IApiConverter.IMPL.poListToVoList(apiList);
        return apis;
    }

    @Override
    public List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam) {
        return apiMapper.queryApiPageList(apiQueryParam);
    }
}
