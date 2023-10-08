package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.domain.api.vo.ApiVO;
import net.somta.juggle.console.infrastructure.po.ApiPO;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;

import java.util.List;

/**
 * @author Gavin
 */
public interface ApiMapper extends IBaseMapper {

    /**
     * @param apiPO
     * @return
     */
    int addApi(ApiPO apiPO);

    List<ApiPO> queryApiListByDomainId(Long domainId);

    Long queryApiCount(ApiQueryParam apiQueryParam);

    List<ApiVO> queryApiPageList(ApiQueryParam apiQueryParam);
}
