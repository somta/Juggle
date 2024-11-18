package net.somta.juggle.console.infrastructure.mapper.suite;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.suite.api.vo.ApiVO;
import net.somta.juggle.console.infrastructure.po.suite.ApiPO;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;

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
