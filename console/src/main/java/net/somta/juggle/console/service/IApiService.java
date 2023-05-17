package net.somta.juggle.console.service;

import net.somta.core.base.IBaseService;
import net.somta.juggle.console.model.Api;

import java.util.List;

public interface IApiService extends IBaseService<Api> {


    List<Api> getApiListByDomainId(Long domainId);

    /**
     * 获取Api列表
     * @return
     */
    List<Api> getApiList();


}
