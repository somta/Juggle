package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.service.IApiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements IApiService {
    @Override
    public List<Api> getApiList() {
        return null;
    }
}
