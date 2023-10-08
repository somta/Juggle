package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.DomainPO;

import java.util.List;

public interface DomainMapper extends IBaseMapper {

    List<DomainPO> queryDomainList();
}
