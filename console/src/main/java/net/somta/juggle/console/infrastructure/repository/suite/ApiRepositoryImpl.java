/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.infrastructure.repository.suite;

import net.somta.core.exception.BizException;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.suite.api.ApiAO;
import net.somta.juggle.console.domain.suite.api.enums.ApiErrorEnum;
import net.somta.juggle.console.domain.suite.api.repository.IApiRepository;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.converter.suite.IApiConverter;
import net.somta.juggle.console.infrastructure.mapper.suite.ApiMapper;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.suite.ApiPO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gavin
 * @since 1.0.0
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
        ApiPO apiPo = IApiConverter.IMPL.aoToPo(apiAo);
        apiPo.setCreatedAt(new Date());
        apiPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        apiMapper.addApi(apiPo);

        List<ParameterPO> parameterPoList = getApiParameterPoList(apiAo,apiPo.getId());
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterMapper.batchAddParameter(parameterPoList);
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
        ApiPO apiPo = IApiConverter.IMPL.aoToPo(apiAo);
        apiPo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        apiPo.setUpdatedAt(new Date());
        apiMapper.update(apiPo);

        parameterMapper.deleteParameter(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiAo.getId()));
        List<ParameterPO> parameterPoList = getApiParameterPoList(apiAo,apiAo.getId());
        if(CollectionUtils.isNotEmpty(parameterPoList)){
            parameterMapper.batchAddParameter(parameterPoList);
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
        List<ParameterPO> parameters = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiId));

        apiAo.parseHeader(parameters);

        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.parseParameter(parameters);
        apiAo.setParameterEntity(parameterEntity);
        return apiAo;
    }

    @Override
    public ApiAO queryApiByCode(String apiCode) {
        ApiPO apiPo = apiMapper.queryApiByCode(apiCode);
        if(apiPo == null){
            throw new BizException(ApiErrorEnum.API_NOT_EXIST);
        }
        ApiAO apiAo = IApiConverter.IMPL.poToAo(apiPo);
        List<ParameterPO> parameters = parameterMapper.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),apiPo.getId()));

        apiAo.parseHeader(parameters);

        ParameterEntity parameterEntity = new ParameterEntity();
        parameterEntity.parseParameter(parameters);
        apiAo.setParameterEntity(parameterEntity);
        return apiAo;
    }

    @Override
    public List<ApiVO> getApiListBySuiteId(Long suiteId) {
        List<ApiPO> apiList = apiMapper.queryApiListBySuiteId(suiteId);
        List<ApiVO> apis = IApiConverter.IMPL.poListToVoList(apiList);
        return apis;
    }

    @Override
    public List<ApiVO> getApiListBySuiteCode(String suiteCode) {
        List<ApiPO> apiList = apiMapper.queryApiListBySuiteCode(suiteCode);
        List<ApiVO> apis = IApiConverter.IMPL.poListToVoList(apiList);
        return apis;
    }

    @Override
    public List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam) {
        return apiMapper.queryApiPageList(apiQueryParam);
    }

    /**
     * Retrieve the persistent object list of the API
     * @param apiAo API Aggregate Root Object
     * @param sourceId API id
     * @return Parameter Persistent object list
     */
    private List<ParameterPO> getApiParameterPoList(ApiAO apiAo,Long sourceId){
        List<ParameterPO> parameterPoList = new ArrayList<>();
        List<ParameterPO> parameterList = apiAo.getParameterEntity().getParameterPoList(sourceId,ParameterSourceTypeEnum.API.getCode());
        parameterPoList.addAll(parameterList);

        List<ParameterPO> headerList = IApiConverter.IMPL.headerListToParameterList(sourceId,apiAo.getApiHeaders());
        parameterPoList.addAll(headerList);
        return parameterPoList;
    }
}
