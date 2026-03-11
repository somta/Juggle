package com.matecoder.juggle.console.domain.suite.api.repository;

import com.matecoder.juggle.console.domain.suite.api.ApiAO;
import com.matecoder.juggle.console.domain.suite.api.vo.ApiVO;
import com.matecoder.juggle.console.interfaces.param.suite.ApiQueryParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface IApiRepository {

    Boolean addApi(ApiAO apiAo);

    Boolean deleteApi(Long apiId);

    Boolean updateApi(ApiAO apiAo);

    ApiAO queryApi(Long apiId);

    ApiAO queryApiByCode(String apiCode);

    List<ApiVO> getApiListBySuiteId(Long suiteId);

    List<ApiVO> getApiListBySuiteCode(String suiteCode);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);

}
