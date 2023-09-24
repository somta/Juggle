package net.somta.juggle.console.application.service.impl;

import net.somta.core.base.BaseServiceImpl;
import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.mapper.DomainMapper;
import net.somta.juggle.console.infrastructure.po.DomainPO;
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
        DomainPO domainPO = new DomainPO();
        Date date = new Date();
        domainPO.setDomainCode(domainAddParam.getDomainCode());
        domainPO.setDomainName(domainAddParam.getDomainName());
        domainPO.setDomainDesc(domainAddParam.getDomainDesc());
        domainPO.setCreatedAt(date);
        domainMapper.add(domainPO);
    }

    @Override
    public void deleteDomain(Long domainId) {
        domainMapper.deleteById(domainId);
    }

    @Override
    public void updateDomain(DomainUpdateParam domainUpdateParam) {
        DomainPO domainPO = new DomainPO();
        domainPO.setId(domainUpdateParam.getId());
        domainPO.setDomainCode(domainUpdateParam.getDomainCode());
        domainPO.setDomainName(domainUpdateParam.getDomainName());
        domainPO.setDomainDesc(domainUpdateParam.getDomainDesc());
        domainPO.setUpdatedAt(new Date());
        domainMapper.update(domainPO);
    }

    @Override
    public List<DomainDTO> getDomainList() {
        return domainMapper.queryDomainList();
    }

}
