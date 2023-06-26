package net.somta.juggle.console.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.dto.ApiDTO;
import net.somta.juggle.console.model.param.ApiQueryParam;

import java.util.List;

public interface ApiMapper extends IBaseMapper {

    int addApi(Api api);

    List<Api> queryApiListByDomainId(Long domainId);

    List<Api> queryApiList();

    Long queryApiCount(ApiQueryParam apiQueryParam);

    List<ApiDTO> queryApiPageList(ApiQueryParam apiQueryParam);
}
