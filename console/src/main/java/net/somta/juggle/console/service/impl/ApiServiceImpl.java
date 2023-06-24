package net.somta.juggle.console.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.mapper.ApiMapper;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.dto.ApiDTO;
import net.somta.juggle.console.model.param.ApiQueryParam;
import net.somta.juggle.console.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl extends BaseServiceImpl<Api> implements IApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public IBaseMapper getMapper() {
        return apiMapper;
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


}
