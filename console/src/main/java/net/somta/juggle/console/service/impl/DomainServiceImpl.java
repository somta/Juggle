package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.mapper.DomainMapper;
import net.somta.juggle.console.model.Domain;
import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.DomainAddParam;
import net.somta.juggle.console.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DomainServiceImpl implements IDomainService {

    @Autowired
    private DomainMapper domainMapper;

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
    public List<DomainDTO> getDomainList() {
        return domainMapper.queryDomainList();
    }
}
