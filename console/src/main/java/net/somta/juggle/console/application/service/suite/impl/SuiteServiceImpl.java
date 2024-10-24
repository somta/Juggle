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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.core.exception.BizException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.suite.ISuiteAssembler;
import net.somta.juggle.console.application.service.IObjectService;
import net.somta.juggle.console.application.service.suite.IApiService;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.object.ObjectAO;
import net.somta.juggle.console.domain.suite.suiteinfo.SuiteEntity;
import net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteTypeEnum;
import net.somta.juggle.console.domain.suite.suiteinfo.repository.ISuiteRepository;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.*;
import net.somta.juggle.console.interfaces.dto.suite.SuiteDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketClassifyDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.ObjectAddParam;
import net.somta.juggle.console.interfaces.param.suite.*;
import net.somta.juggle.core.enums.RequestTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final IObjectService objectService;

    public SuiteServiceImpl(ISuiteRepository suiteRepository, IApiService apiService, IObjectService objectService) {
        this.suiteRepository = suiteRepository;
        this.apiService = apiService;
        this.objectService = objectService;
    }

    @Override
    public void addSuite(SuiteAddParam suiteAddParam) {
        SuiteVO suiteVo = suiteRepository.querySuiteByCode(suiteAddParam.getSuiteCode());
        if(suiteVo != null){
            throw new BizException(SUITE_IS_EXIST_ERROR,suiteAddParam.getSuiteCode());
        }
        SuiteEntity suiteEntity = ISuiteAssembler.IMPL.paramToEntity(suiteAddParam);
        suiteEntity.setSuiteVersion("v1.0.0");
        suiteEntity.setSuiteFlag(SuiteTypeEnum.PERSONAL_SUITE.getCode());
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
    public SuiteVO getSuiteInfo(Long suiteId) {
        return suiteRepository.querySuiteById(suiteId);
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
    public List<SuiteMarketClassifyDTO> getSuiteMarketClassifyList() {
        List<SuiteMarketClassifyVO> suiteMarketClassifyVoList = suiteRepository.querySuiteMarketClassifyList();
        List<SuiteMarketClassifyDTO> classifyDtoList = ISuiteAssembler.IMPL.voSuiteMarketListToDtoList(suiteMarketClassifyVoList);
        return classifyDtoList;
    }

    @Override
    public ResponsePaginationDataResult<SuiteDTO> getSuiteMarketList(SuiteMarketQueryParam suiteMarketQueryParam) {
        ResponsePaginationDataResult<SuiteVO> result = suiteRepository.querySuiteMarketList(suiteMarketQueryParam.getPageNum(),suiteMarketQueryParam.getPageSize(),suiteMarketQueryParam.getSuiteName(),suiteMarketQueryParam.getSuiteClassifyId());
        List<SuiteDTO> suiteList = ISuiteAssembler.IMPL.voListToDtoList(result.getResult());
        return ResponsePaginationDataResult.setPaginationDataResult(result.getTotal(),suiteList);
    }

    @Override
    public SuiteMarketInfoDTO getSuiteMarketInfo(Long suiteId) {
        SuiteMarketVO suiteMarketVo = suiteRepository.querySuiteMarketInfo(suiteId,null);
        SuiteMarketInfoDTO suiteMarketDTO = ISuiteAssembler.IMPL.voToDto(suiteMarketVo);
        if(suiteMarketVo != null){
            SuiteVO suiteVo = suiteRepository.querySuiteByCode(suiteMarketVo.getSuiteCode());
            if(suiteVo != null){
                suiteMarketDTO.setInstallStatus(true);
            }
        }
        return suiteMarketDTO;
    }

    @Transactional
    @Override
    public Boolean installSuiteMarket(SuiteMarketParam suiteMarketParam) {
        SuiteMarketVO suiteMarketVo = suiteRepository.querySuiteMarketInfo(suiteMarketParam.getSuiteId(),suiteMarketParam.getBill());
        if(suiteMarketVo == null){
            throw new BizException(SUITE_NOT_EXIST_ERROR);
        }
        SuiteEntity suiteEntity = new SuiteEntity();
        suiteEntity.setSuiteCode(suiteMarketVo.getSuiteCode());
        suiteEntity.setSuiteName(suiteMarketVo.getSuiteName());
        suiteEntity.setSuiteImage(suiteMarketVo.getSuiteImage());
        suiteEntity.setSuiteVersion(suiteMarketVo.getSuiteVersion());
        suiteEntity.setSuiteDesc(suiteMarketVo.getSuiteDesc());
        suiteEntity.setSuiteHelpDocJson(suiteMarketVo.getSuiteHelpDocJson());
        suiteEntity.setSuiteFlag(SuiteTypeEnum.OFFICIAL_SUITE.getCode());
        Long newSuiteId = suiteRepository.addSuite(suiteEntity);
        addSuiteObjectList(suiteMarketVo.getObjectList());
        List<SuiteMarketApiVO> apiList = suiteMarketVo.getApiList();
        if(CollectionUtils.isNotEmpty(apiList)){
            for (SuiteMarketApiVO suiteMarketApi : apiList) {
                addSuiteMarketApi(newSuiteId,suiteMarketApi);
            }
        }
        return true;
    }

    /**
     * add suite object
     * @param objectListStr suite object list
     */
    private void addSuiteObjectList(String objectListStr) {
        if(StringUtils.isNotBlank(objectListStr)){
            List<ObjectAddParam> objectList = null;
            try {
                objectList = JsonSerializeHelper.deserialize(objectListStr, List.class, ObjectAddParam.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            for (ObjectAddParam objectAddParam : objectList){
                ObjectAO objectAo = objectService.getObjectInfoByKey(objectAddParam.getObjectKey());
                if(objectAo == null){
                    objectService.addObject(objectAddParam);
                }
            }
        }
    }

    /**
     * List of interfaces for newly added packages
     * @param suiteMarketApi suite api list
     */
    private void addSuiteMarketApi(Long suiteId,SuiteMarketApiVO suiteMarketApi){
        ApiAddParam apiAddParam = new ApiAddParam();
        apiAddParam.setSuiteId(suiteId);
        apiAddParam.setApiCode(suiteMarketApi.getApiCode());
        apiAddParam.setApiUrl(suiteMarketApi.getApiUrl());
        apiAddParam.setApiName(suiteMarketApi.getApiName());
        apiAddParam.setApiDesc(suiteMarketApi.getApiDesc());
        apiAddParam.setApiRequestType(RequestTypeEnum.valueOf(suiteMarketApi.getApiRequestType()));
        apiAddParam.setApiRequestContentType(suiteMarketApi.getApiRequestContentType());
        apiAddParam.setApiHeaders(suiteMarketApi.getApiHeaders());
        apiAddParam.setApiInputParams(suiteMarketApi.getApiInputParams());
        apiAddParam.setApiOutputParams(suiteMarketApi.getApiOutputParams());
        apiService.addApi(apiAddParam);
    }
}
