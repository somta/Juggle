package com.matecoder.juggle.console.infrastructure.mapper.suite;

import com.matecoder.core.base.IBaseMapper;
import com.matecoder.juggle.console.domain.suite.api.vo.ApiVO;
import com.matecoder.juggle.console.infrastructure.po.suite.ApiPO;
import com.matecoder.juggle.console.interfaces.param.suite.ApiQueryParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface ApiMapper extends IBaseMapper {

    /**
     * @param apiPo
     * @return
     */
    int addApi(ApiPO apiPo);

    ApiPO queryApiByCode(String apiCode);

    List<ApiPO> queryApiListBySuiteId(Long suiteId);

    List<ApiPO> queryApiListBySuiteCode(String suiteCode);

    Long queryApiCount(ApiQueryParam apiQueryParam);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);

}
