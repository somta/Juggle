package net.somta.juggle.console.service;


import net.somta.core.base.IBaseService;
import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.DomainAddParam;
import net.somta.juggle.console.model.param.DomainQueryParam;
import net.somta.juggle.console.model.param.DomainUpdateParam;

import java.util.List;

public interface IDomainService extends IBaseService {


    void addDomain(DomainAddParam domainAddParam);

    void deleteDomain(Long domainId);


    void updateDomain(DomainUpdateParam domainUpdateParam);

    List<DomainDTO> getDomainList();

}
