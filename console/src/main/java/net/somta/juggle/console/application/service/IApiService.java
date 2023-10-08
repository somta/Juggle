package net.somta.juggle.console.application.service;

import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface IApiService {

    /**
     *
     * @param apiAddParam
     * @return
     */
    ResponseDataResult<Boolean> addApi(ApiAddParam apiAddParam);

    ResponseDataResult<Boolean> deleteApi(Long apiId);

    ResponseDataResult<Boolean> updateApi(ApiUpdateParam apiUpdateParam);

    ApiInfoDTO queryApiInfo(Long apiId);

    List<ApiDTO> getApiListByDomainId(Long domainId);

    ResponsePaginationDataResult<List<ApiDTO>> queryApiPageList(ApiQueryParam apiQueryParam);

}
