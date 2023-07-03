package net.somta.juggle.console.application.service;

import net.somta.core.base.IBaseService;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.infrastructure.model.Api;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;

import java.util.List;

public interface IApiService extends IBaseService<Api> {

    ResponseDataResult<Boolean> addApi(ApiAddParam apiAddParam);

    ResponseDataResult<Boolean> updateApi(ApiUpdateParam apiUpdateParam);

    List<Api> getApiListByDomainId(Long domainId);

    /**
     * 获取Api列表
     * @return
     */
    List<Api> getApiList();


    ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam);


}
