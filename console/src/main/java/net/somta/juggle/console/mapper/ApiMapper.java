package net.somta.juggle.console.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.model.Api;

import java.util.List;

public interface ApiMapper extends IBaseMapper {



    List<Api> queryApiListByDomainId(Long domainId);


    List<Api> queryApiList();
}
