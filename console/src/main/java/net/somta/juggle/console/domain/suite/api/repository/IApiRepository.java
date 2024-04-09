package net.somta.juggle.console.domain.suite.api.repository;

import net.somta.juggle.console.domain.suite.api.ApiAO;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface IApiRepository {

    Boolean addApi(ApiAO apiAo);

    Boolean deleteApi(Long apiId);

    Boolean updateApi(ApiAO apiAo);

    ApiAO queryApi(Long apiId);

    List<ApiVO> getApiListBySuiteId(Long suiteId);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);
}
