package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.repository.IDomainRepository;
import net.somta.juggle.console.domain.domain.vo.DomainQueryVO;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.infrastructure.converter.IDomainConverter;
import net.somta.juggle.console.infrastructure.mapper.DomainMapper;
import net.somta.juggle.console.infrastructure.po.DomainPO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Repository
public class DomainRepositoryImpl implements IDomainRepository {
    private final DomainMapper domainMapper;

    public DomainRepositoryImpl(DomainMapper domainMapper) {
        this.domainMapper = domainMapper;
    }

    @Override
    public void addDomain(DomainEntity domainEntity) {
        DomainPO domainPo = IDomainConverter.IMPL.entityToPo(domainEntity);
        domainPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
        domainPo.setCreatedAt(new Date());
        domainMapper.add(domainPo);
    }

    @Override
    public void deleteDomainById(Long domainId) {
        domainMapper.deleteById(domainId);
    }

    @Override
    public void updateDomain(Long domainId, DomainEntity domainEntity) {
        DomainPO domainPo = IDomainConverter.IMPL.entityToPo(domainEntity);
        domainPo.setId(domainId);
        domainPo.setUpdatedBy(IdentityContext.getIdentity().getUserId());
        domainPo.setUpdatedAt(new Date());
        domainMapper.update(domainPo);
    }

    @Override
    public List<DomainVO> queryDomainListNoPage() {
        List<DomainPO> domainPoList = domainMapper.queryDomainList();
        List<DomainVO> domainVoList = IDomainConverter.IMPL.poListToVoList(domainPoList);
        return domainVoList;
    }

    @Override
    public List<DomainVO> queryDomainList(DomainQueryVO domainQueryVO) {
        List<DomainPO> domainPoList = domainMapper.queryByList(domainQueryVO);
        List<DomainVO> domainVoList = IDomainConverter.IMPL.poListToVoList(domainPoList);
        return domainVoList;
    }
}
