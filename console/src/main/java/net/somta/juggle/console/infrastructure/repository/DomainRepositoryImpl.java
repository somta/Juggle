package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.repository.IDomainRepository;
import net.somta.juggle.console.domain.domain.vo.DomainQueryVO;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.infrastructure.converter.IDomainConverter;
import net.somta.juggle.console.infrastructure.mapper.DomainMapper;
import net.somta.juggle.console.infrastructure.po.DomainPO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DomainRepositoryImpl implements IDomainRepository {
    private final DomainMapper domainMapper;

    public DomainRepositoryImpl(DomainMapper domainMapper) {
        this.domainMapper = domainMapper;
    }

    @Override
    public void addDomain(DomainEntity domainEntity) {
        DomainPO domainPO = IDomainConverter.IMPL.entityToPo(domainEntity);
        domainPO.setCreatedAt(new Date());
        domainMapper.add(domainPO);
    }

    @Override
    public void deleteDomainById(Long domainId) {
        domainMapper.deleteById(domainId);
    }

    @Override
    public void updateDomain(Long domainId, DomainEntity domainEntity) {
        DomainPO domainPO = IDomainConverter.IMPL.entityToPo(domainEntity);
        domainPO.setId(domainId);
        domainMapper.update(domainPO);
    }

    @Override
    public List<DomainVO> queryDomainListNoPage() {
        List<DomainPO> domainPOList = domainMapper.queryDomainList();
        List<DomainVO> domainVOList = IDomainConverter.IMPL.poListToVoList(domainPOList);
        return domainVOList;
    }

    @Override
    public List<DomainVO> queryDomainList(DomainQueryVO domainQueryVO) {
        List<DomainPO> domainPOList = domainMapper.queryByList(domainQueryVO);
        List<DomainVO> domainVOList = IDomainConverter.IMPL.poListToVoList(domainPOList);
        return domainVOList;
    }
}
