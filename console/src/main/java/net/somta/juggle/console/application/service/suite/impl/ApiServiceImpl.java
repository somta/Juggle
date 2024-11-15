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
package net.somta.juggle.console.application.service.suite.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.suite.IApiAssembler;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.suite.api.ApiAO;
import net.somta.juggle.console.domain.suite.api.repository.IApiRepository;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import net.somta.juggle.console.interfaces.dto.suite.ApiDTO;
import net.somta.juggle.console.application.service.suite.IApiService;
import net.somta.juggle.console.interfaces.param.suite.ApiAddParam;
import net.somta.juggle.console.interfaces.param.suite.ApiDebugParam;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.suite.ApiUpdateParam;
import net.somta.juggle.core.enums.RequestContentTypeEnum;
import net.somta.juggle.core.http.HttpClientFactory;
import net.somta.juggle.core.http.IHttpClient;
import net.somta.juggle.core.http.Request;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class ApiServiceImpl implements IApiService {

    private final IApiRepository apiRepository;
    private final ISuiteRepository suiteRepository;

    public ApiServiceImpl(IApiRepository apiRepository, ISuiteRepository suiteRepository) {
        this.apiRepository = apiRepository;
        this.suiteRepository = suiteRepository;
    }

    @Override
    public Boolean addApi(ApiAddParam apiAddParam) {
        ApiAO apiAo = IApiAssembler.IMPL.paramToAo(apiAddParam);
        apiAo.initApiCode();
        apiAo.initParameterList(apiAddParam.getApiInputParams(),apiAddParam.getApiOutputParams());
        apiAo.initHeaderList(apiAddParam.getApiHeaders());
        return apiRepository.addApi(apiAo);
    }

    @Override
    public Boolean deleteApi(Long apiId) {
        return apiRepository.deleteApi(apiId);
    }

    @Override
    public Boolean updateApi(ApiUpdateParam apiUpdateParam) {
        ApiAO apiAo = IApiAssembler.IMPL.paramToAo(apiUpdateParam);
        apiAo.initApiCode();
        apiAo.initParameterList(apiUpdateParam.getApiInputParams(),apiUpdateParam.getApiOutputParams());
        apiAo.initHeaderList(apiUpdateParam.getApiHeaders());
        return apiRepository.updateApi(apiAo);
    }

    @Override
    public ApiInfoDTO getApiInfo(Long apiId) {
        ApiAO apiAo = apiRepository.queryApi(apiId);
        ApiInfoDTO apiInfoDto = IApiAssembler.IMPL.aoToDto(apiAo);
        SuiteVO suiteVo = suiteRepository.querySuiteById(apiAo.getSuiteId());
        apiInfoDto.setSuiteFlag(suiteVo.getSuiteFlag());
        return apiInfoDto;
    }

    @Override
    public ApiInfoDTO getApiInfoByCode(String apiCode) {
        ApiAO apiAo = apiRepository.queryApiByCode(apiCode);
        ApiInfoDTO apiInfoDto = IApiAssembler.IMPL.aoToDto(apiAo);
        return apiInfoDto;
    }

    @Override
    public List<ApiDTO> getApiListBySuiteId(Long suiteId) {
        List<ApiVO> apiVoList = apiRepository.getApiListBySuiteId(suiteId);
        List<ApiDTO> apiDtoList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        return apiDtoList;
    }

    @Override
    public List<ApiDTO> getApiListBySuiteCode(String suiteCode) {
        List<ApiVO> apiVoList = apiRepository.getApiListBySuiteCode(suiteCode);
        return IApiAssembler.IMPL.voListToDtoList(apiVoList);
    }

    @Override
    public PageInfo getApiPageList(ApiQueryParam apiQueryParam) {
        Page<ApiDTO> page = PageHelper.startPage(apiQueryParam.getPageNum(), apiQueryParam.getPageSize());
        List<ApiVO> apiVoList = apiRepository.queryApiPageList(apiQueryParam);
        List<ApiDTO> apiList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        PageInfo pageInfo = new PageInfo(apiList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public Map<String, Object> debugApi(Long apiId, ApiDebugParam apiDebugParam) {
        ApiAO apiAo = apiRepository.queryApi(apiId);
        IHttpClient httpClient = HttpClientFactory.getHttpClient(RequestContentTypeEnum.findEnumByValue(apiAo.getApiRequestContentType()));
        Request request = new Request(apiAo.getApiRequestType(),apiAo.getParameterEntity().getInputParameterSchema());
        request.initRequest(apiAo.getApiCode(),apiAo.getApiUrl(),apiDebugParam.getHeaderData(),apiDebugParam.getInputParamData());
        Map<String,Object> originalResult = httpClient.sendRequest(request);
        Map<String,Object> result = apiAo.handleApiResponseResult(originalResult);
        return result;
    }

}
