package net.somta.juggle.console.application.service;


import net.somta.core.base.IBaseService;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;

import java.util.List;

public interface IDomainService extends IBaseService {


    void addDomain(DomainAddParam domainAddParam);

    void deleteDomain(Long domainId);


    void updateDomain(DomainUpdateParam domainUpdateParam);

    List<DomainDTO> getDomainList();

}
