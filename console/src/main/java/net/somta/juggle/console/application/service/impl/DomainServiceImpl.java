package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.mapper.DomainMapper;
import net.somta.juggle.console.infrastructure.model.Domain;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import net.somta.juggle.console.application.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DomainServiceImpl extends BaseServiceImpl implements IDomainService {

    @Autowired
    private DomainMapper domainMapper;

    @Override
    public IBaseMapper getMapper() {
        return domainMapper;
    }

    @Override
    public void addDomain(DomainAddParam domainAddParam) {
        Domain domain = new Domain();
        Date date = new Date();
        domain.setDomainCode(domainAddParam.getDomainCode());
        domain.setDomainName(domainAddParam.getDomainName());
        domain.setDomainDesc(domainAddParam.getDomainDesc());
        domain.setCreatedAt(date);
        domainMapper.add(domain);
    }

    @Override
    public void deleteDomain(Long domainId) {
        domainMapper.deleteById(domainId);
    }

    @Override
    public void updateDomain(DomainUpdateParam domainUpdateParam) {
        Domain domain = new Domain();
        domain.setId(domainUpdateParam.getId());
        domain.setDomainCode(domainUpdateParam.getDomainCode());
        domain.setDomainName(domainUpdateParam.getDomainName());
        domain.setDomainDesc(domainUpdateParam.getDomainDesc());
        domain.setUpdatedAt(new Date());
        domainMapper.update(domain);
    }

    @Override
    public List<DomainDTO> getDomainList() {
        return domainMapper.queryDomainList();
    }

}
