package net.somta.juggle.console.mapper;


import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.model.dto.DomainDTO;

import java.util.List;

public interface DomainMapper extends IBaseMapper {

    List<DomainDTO> queryDomainList();
}
