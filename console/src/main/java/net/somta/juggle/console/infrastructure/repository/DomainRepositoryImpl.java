/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
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
 * @since 1.0.0
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
