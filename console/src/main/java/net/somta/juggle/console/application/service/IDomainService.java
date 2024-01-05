package net.somta.juggle.console.application.service;

import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IDomainService {

    /**
     * Add a domain
     * @param domainAddParam Domain add parameters object
     */
    void addDomain(DomainAddParam domainAddParam);

    /**
     * delete a domain
     * @param domainId domain id
     */
    void deleteDomain(Long domainId);

    /**
     * Update domain information
     * @param domainUpdateParam Domain update parameters object
     */
    void updateDomain(DomainUpdateParam domainUpdateParam);

    /**
     * Query domain list information
     * @return Domain List
     */
    List<DomainDTO> getAllDomainList();

    /**
     * Query domain pagination list data
     * @param domainQueryParam Domain query parameter object
     * @return Domain Paging List Object
     */
    PageInfo getDomainPageList(DomainQueryParam domainQueryParam);
}
