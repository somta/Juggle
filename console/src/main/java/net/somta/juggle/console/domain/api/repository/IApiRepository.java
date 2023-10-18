package net.somta.juggle.console.domain.api.repository;

import net.somta.juggle.console.domain.api.ApiAO;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface IApiRepository {

    Boolean addApi(ApiAO apiAO);

    Boolean deleteApi(Long apiId);

    Boolean updateApi(ApiAO apiAO);

    ApiAO queryApi(Long apiId);

    List<ApiVO> getApiListByDomainId(Long domainId);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);
}
