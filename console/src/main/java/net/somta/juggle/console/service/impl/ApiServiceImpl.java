package net.somta.juggle.console.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.mapper.ApiMapper;
import net.somta.juggle.console.model.Api;
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


}
