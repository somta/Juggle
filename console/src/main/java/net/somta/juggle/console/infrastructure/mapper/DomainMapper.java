package net.somta.juggle.console.infrastructure.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.interfaces.dto.DomainDTO;

import java.util.List;

public interface DomainMapper extends IBaseMapper {

    List<DomainDTO> queryDomainList();
}
