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
import net.somta.core.exception.BizException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.application.assembler.suite.ISuiteAssembler;
import net.somta.juggle.console.application.service.suite.IApiService;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketApiVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteMarketVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteQueryVO;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketDTO;
import net.somta.juggle.console.interfaces.param.suite.ApiAddParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteAddParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteQueryParam;
import net.somta.juggle.console.interfaces.param.suite.SuiteUpdateParam;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteErrorEnum.SUITE_IS_EXIST_ERROR;
import static net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteErrorEnum.SUITE_NOT_EXIST_ERROR;

/**
 * @author husong
 * @since 1.1.1
 */
@Service
public class SuiteServiceImpl implements ISuiteService {
    private final ISuiteRepository suiteRepository;
    private final IApiService apiService;

    public SuiteServiceImpl(ISuiteRepository suiteRepository,IApiService apiService) {
        this.suiteRepository = suiteRepository;
        this.apiService = apiService;
    }

    @Override
    public void addSuite(SuiteAddParam suiteAddParam) {
        SuiteVO suiteVo = suiteRepository.querySuiteByCode(suiteAddParam.getSuiteCode());
        if(suiteVo != null){
            throw new BizException(SUITE_IS_EXIST_ERROR,suiteAddParam.getSuiteCode());
        }
        SuiteEntity suiteEntity = ISuiteAssembler.IMPL.paramToEntity(suiteAddParam);
        suiteRepository.addSuite(suiteEntity);
    }

    @Override
    public void updateSuite(SuiteUpdateParam suiteUpdateParam) {
        SuiteVO suiteVo = suiteRepository.querySuiteByCode(suiteUpdateParam.getSuiteCode());
        if(suiteVo != null
                && !suiteVo.getId().equals(suiteUpdateParam.getId())
                && suiteUpdateParam.getSuiteCode().equals(suiteVo.getSuiteCode())){
            throw new BizException(SUITE_IS_EXIST_ERROR,suiteUpdateParam.getSuiteCode());
        }
        SuiteEntity suiteEntity = ISuiteAssembler.IMPL.paramToEntity(suiteUpdateParam);
        suiteRepository.updateSuite(suiteEntity);
    }

    @Override
    public void deleteSuite(Long suiteId) {
        suiteRepository.deleteSuiteById(suiteId);
    }

    @Override
    public PageInfo getSuitePageList(SuiteQueryParam suiteQueryParam) {
        Page<SuiteDTO> page = PageHelper.startPage(suiteQueryParam.getPageNum(), suiteQueryParam.getPageSize());
        SuiteQueryVO suiteQueryVo = new SuiteQueryVO();
        suiteQueryVo.setSuiteName(suiteQueryParam.getSuiteName());
        List<SuiteVO> suiteVoList = suiteRepository.querySuiteList(suiteQueryVo);
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(suiteVoList);
        PageInfo pageInfo = new PageInfo(suiteList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public List<SuiteDTO> getAllSuiteList() {
        List<SuiteVO> suiteVoList = suiteRepository.querySuiteList(null);
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(suiteVoList);
        return suiteList;
    }

    @Override
    public List<SuiteDTO> getSuiteMarketList() {
        List<SuiteVO> suiteVoList = suiteRepository.querySuiteMarketList();
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(suiteVoList);
        return suiteList;
    }

    @Override
    public SuiteMarketDTO getSuiteMarketInfo(Long suiteId) {
        SuiteMarketVO suiteMarketVo = suiteRepository.querySuiteMarketInfo(suiteId);
        SuiteMarketDTO suiteMarketDTO = ISuiteAssembler.IMPL.voToDto(suiteMarketVo);
        return suiteMarketDTO;
    }

    @Transactional
    @Override
    public Boolean installSuiteMarket(Long suiteId) {
        SuiteMarketVO suiteMarketVo = suiteRepository.querySuiteMarketInfo(suiteId);
        if(suiteMarketVo == null){
            throw new BizException(SUITE_NOT_EXIST_ERROR);
        }
        SuiteEntity suiteEntity = new SuiteEntity();
        suiteEntity.setSuiteCode(suiteMarketVo.getSuiteCode());
        suiteEntity.setSuiteName(suiteMarketVo.getSuiteName());
        suiteEntity.setSuiteImage(suiteMarketVo.getSuiteImage());
        suiteEntity.setSuiteDesc(suiteMarketVo.getSuiteDesc());
        suiteEntity.setSuiteHelpDocJson(suiteMarketVo.getSuiteHelpDocJson());
        suiteRepository.addSuite(suiteEntity);
        List<SuiteMarketApiVO> apiList = suiteMarketVo.getApiList();
        if(CollectionUtils.isNotEmpty(apiList)){
            for (SuiteMarketApiVO suiteMarketApi : apiList) {
                addSuiteMarketApi(suiteMarketApi);
            }
        }
        return true;
    }

    private void addSuiteMarketApi(SuiteMarketApiVO suiteMarketApi){
        ApiAddParam apiAddParam = new ApiAddParam();
        apiAddParam.setApiUrl(suiteMarketApi.getApiUrl());
        apiAddParam.setApiName(suiteMarketApi.getApiName());
        apiAddParam.setApiDesc(suiteMarketApi.getApiDesc());
        apiAddParam.setApiRequestType(RequestTypeEnum.valueOf(suiteMarketApi.getApiRequestType()));
        apiAddParam.setApiRequestContentType(suiteMarketApi.getApiRequestContentType());
        String apiHeaders = suiteMarketApi.getApiHeaders();
        if(StringUtils.isNotEmpty(apiHeaders)){
            List<HeaderVO> headerVoList = JsonSerializeHelper.deserialize(apiHeaders,List.class, HeaderVO.class);
            apiAddParam.setApiHeaders(headerVoList);
        }
        String apiInputParameter = suiteMarketApi.getApiInputParameter();
        if(StringUtils.isNotEmpty(apiInputParameter)){
            List<InputParameterVO> inputParameterVoList = JsonSerializeHelper.deserialize(apiInputParameter,List.class, InputParameterVO.class);
            apiAddParam.setApiInputParams(inputParameterVoList);
        }
        String apiOutputParameter = suiteMarketApi.getApiOutputParameter();
        if(StringUtils.isNotEmpty(apiOutputParameter)){
            List<OutputParameterVO> outputParameterVoList = JsonSerializeHelper.deserialize(apiOutputParameter,List.class, OutputParameterVO.class);
            apiAddParam.setApiOutputParams(outputParameterVoList);
        }
        apiService.addApi(apiAddParam);
    }
}
