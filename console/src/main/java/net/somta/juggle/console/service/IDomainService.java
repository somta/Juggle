package net.somta.juggle.console.service;


import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.DomainAddParam;

import java.util.List;

public interface IDomainService {


    void addDomain(DomainAddParam domainAddParam);

    List<DomainDTO> getDomainList();
}
