package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.model.Api;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;

import java.util.List;

public interface ApiMapper extends IBaseMapper {

    int addApi(Api api);

    List<Api> queryApiListByDomainId(Long domainId);

    List<Api> queryApiList();

    Long queryApiCount(ApiQueryParam apiQueryParam);

    List<ApiDTO> queryApiPageList(ApiQueryParam apiQueryParam);
}
