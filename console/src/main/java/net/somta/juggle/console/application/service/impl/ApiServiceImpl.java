package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

/**
 * @author husong
 */
@Service
public class ApiServiceImpl implements IApiService {

    private final IApiRepository apiRepository;

    public ApiServiceImpl(IApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public Boolean addApi(ApiAddParam apiAddParam) {
        ApiAO apiAo = IApiAssembler.IMPL.paramToAo(apiAddParam);
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
        apiAo.initParameterList(apiUpdateParam.getApiInputParams(),apiUpdateParam.getApiOutputParams());
        apiAo.initHeaderList(apiUpdateParam.getApiHeaders());
        return apiRepository.updateApi(apiAo);
    }

    @Override
    public ApiInfoDTO getApiInfo(Long apiId) {
        ApiAO apiAo = apiRepository.queryApi(apiId);
        ApiInfoDTO apiInfoDto = IApiAssembler.IMPL.aoToDto(apiAo);
        return apiInfoDto;
    }

    @Override
    public List<ApiDTO> getApiListByDomainId(Long domainId) {
        List<ApiVO> apiVoList = apiRepository.getApiListByDomainId(domainId);
        List<ApiDTO> apiDtoList = IApiAssembler.IMPL.voListToDtoList(apiVoList);
        return apiDtoList;
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

}
