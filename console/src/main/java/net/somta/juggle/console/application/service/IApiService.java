package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
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
    Boolean addApi(ApiAddParam apiAddParam);

    Boolean deleteApi(Long apiId);

    Boolean updateApi(ApiUpdateParam apiUpdateParam);

    ApiInfoDTO getApiInfo(Long apiId);

    List<ApiDTO> getApiListByDomainId(Long domainId);

    PageInfo getApiPageList(ApiQueryParam apiQueryParam);

}
