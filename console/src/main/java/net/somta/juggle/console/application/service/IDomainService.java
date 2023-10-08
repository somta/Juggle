package net.somta.juggle.console.application.service;


import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;

import java.util.List;

public interface IDomainService {


    void addDomain(DomainAddParam domainAddParam);

    void deleteDomain(Long domainId);


    void updateDomain(DomainUpdateParam domainUpdateParam);

    List<DomainDTO> getAllDomainList();

    ResponsePaginationDataResult<List<DomainDTO>> getDomainPageList(DomainQueryParam domainQueryParam);
}
