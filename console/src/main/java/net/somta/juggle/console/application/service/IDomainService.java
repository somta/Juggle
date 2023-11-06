package net.somta.juggle.console.application.service;


import com.github.pagehelper.PageInfo;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IDomainService {

    void addDomain(DomainAddParam domainAddParam);

    void deleteDomain(Long domainId);


    void updateDomain(DomainUpdateParam domainUpdateParam);

    List<DomainDTO> getAllDomainList();

    PageInfo getDomainPageList(DomainQueryParam domainQueryParam);
}
