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
import net.somta.juggle.console.infrastructure.mapper.ApiMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gavin
 */
@Component
public class ApiRepositoryImpl implements IApiRepository {

    private final ApiMapper apiMapper;
    private final ParameterMapper parameterMapper;

    public ApiRepositoryImpl(ApiMapper apiMapper, ParameterMapper parameterMapper) {
        this.apiMapper = apiMapper;
        this.parameterMapper = parameterMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addApi(ApiAO apiAO) {
        ApiPO apiPO = new ApiPO();
        apiPO.setDomainId(apiAO.getDomainId());
        apiPO.setApiUrl(apiAO.getApiUrl());
        apiPO.setApiName(apiAO.getApiName());
        apiPO.setApiDesc(apiAO.getApiDesc());
        apiPO.setApiRequestType(apiAO.getApiRequestType());
        apiPO.setApiRequestContentType(apiAO.getApiRequestContentType());
        apiPO.setCreatedAt(new Date());
        apiMapper.addApi(apiPO);

        List<ParameterPO> parameterPOS = new ArrayList<>();
        parameterPOS.addAll(apiAO.getParameterEntity().getInputParameterList());
        parameterPOS.addAll(apiAO.getParameterEntity().getOutputParameterList());
        if(CollectionUtils.isNotEmpty(parameterPOS)){
            parameterPOS.stream().forEach(parameter -> parameter.setSourceId(apiPO.getId()));
            parameterMapper.batchAddParameter(parameterPOS);
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
    public Boolean updateApi(ApiAO apiAO) {
        ApiPO apiPO = new ApiPO();
        apiPO.setId(apiAO.getId());
        apiPO.setDomainId(apiAO.getDomainId());
        apiPO.setApiUrl(apiAO.getApiUrl());
        apiPO.setApiName(apiAO.getApiName());
        apiPO.setApiDesc(apiAO.getApiDesc());
        apiPO.setApiRequestType(apiAO.getApiRequestType());
        apiPO.setApiRequestContentType(apiAO.getApiRequestContentType());
        apiMapper.update(apiPO);

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiAO.getId()));
        List<ParameterPO> parameterPOS = new ArrayList<>();
        parameterPOS.addAll(apiAO.getParameterEntity().getInputParameterList());
        parameterPOS.addAll(apiAO.getParameterEntity().getOutputParameterList());
        if(CollectionUtils.isNotEmpty(parameterPOS)){
            parameterMapper.batchAddParameter(parameterPOS);
        }
        return true;
    }

    @Override
    public ApiAO queryApi(Long apiId) {
        ApiPO apiPO = apiMapper.queryById(apiId);
        if(apiPO == null){
            throw new BizException(ApiErrorEnum.API_NOT_EXIST);
        }
        ApiAO apiAO = IApiConverter.IMPL.poToAo(apiPO);

        ParameterEntity parameterEntity = new ParameterEntity();
        List<ParameterPO> parameterPOS = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiId));
        parameterEntity.parseParameter(parameterPOS);
        apiAO.setParameterEntity(parameterEntity);
        return apiAO;
    }

    @Override
    public List<ApiVO> getApiListByDomainId(Long domainId) {
        List<ApiPO> apiList = apiMapper.queryApiListByDomainId(domainId);
        List<ApiVO> apis = IApiConverter.IMPL.poListToVoList(apiList);
        return apis;
    }

    @Override
    public Long queryApiCount(ApiQueryParam apiQueryParam) {
        return apiMapper.queryApiCount(apiQueryParam);
    }

    @Override
    public List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam) {
        return apiMapper.queryApiPageList(apiQueryParam);
    }
}
