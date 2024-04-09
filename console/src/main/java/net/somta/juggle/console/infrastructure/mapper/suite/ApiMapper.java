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

    List<ApiPO> queryApiListBySuiteId(Long suiteId);

    Long queryApiCount(ApiQueryParam apiQueryParam);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);
}
