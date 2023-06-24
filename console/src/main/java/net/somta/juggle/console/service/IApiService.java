package net.somta.juggle.console.service;

import net.somta.core.base.IBaseService;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.dto.ApiDTO;
import net.somta.juggle.console.model.param.ApiQueryParam;

import java.util.List;

public interface IApiService extends IBaseService<Api> {


    List<Api> getApiListByDomainId(Long domainId);

    /**
     * 获取Api列表
     * @return
     */
    List<Api> getApiList();


    ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam);
}
