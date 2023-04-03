package net.somta.juggle.console.service;

import net.somta.juggle.console.model.Api;

import java.util.List;

public interface IApiService {


    /**
     * 获取Api列表
     * @return
     */
    List<Api> getApiList();
}
