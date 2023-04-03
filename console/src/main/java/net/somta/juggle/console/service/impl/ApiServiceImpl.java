package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.mapper.ApiMapper;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements IApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public List<Api> getApiList() {
        return apiMapper.queryApiList();
    }
}
