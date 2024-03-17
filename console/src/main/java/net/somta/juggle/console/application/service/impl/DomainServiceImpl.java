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
package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.juggle.console.application.assembler.IDomainAssembler;
import net.somta.juggle.console.application.service.IDomainService;
import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.repository.IDomainRepository;
import net.somta.juggle.console.domain.domain.vo.DomainQueryVO;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author husong
 * @since 1.0.0
 */
@Service
public class DomainServiceImpl implements IDomainService {

    private final IDomainRepository domainRepository;

    public DomainServiceImpl(IDomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public void addDomain(DomainAddParam domainAddParam) {
        DomainEntity domainEntity = IDomainAssembler.IMPL.paramToEntity(domainAddParam);
        domainRepository.addDomain(domainEntity);
    }

    @Override
    public void deleteDomain(Long domainId) {
        domainRepository.deleteDomainById(domainId);
    }

    @Override
    public void updateDomain(DomainUpdateParam domainUpdateParam) {
        DomainEntity domainEntity = IDomainAssembler.IMPL.paramToEntity(domainUpdateParam);
        domainRepository.updateDomain(domainUpdateParam.getId(),domainEntity);
    }

    @Override
    public List<DomainDTO> getAllDomainList() {
        List<DomainVO> domainVOList = domainRepository.queryDomainListNoPage();
        return IDomainAssembler.IMPL.voListToDtoList(domainVOList);
    }

    @Override
    public PageInfo getDomainPageList(DomainQueryParam domainQueryParam) {
        DomainQueryVO domainQueryVO = IDomainAssembler.IMPL.paramToVo(domainQueryParam);
        Page<DomainDTO> page = PageHelper.startPage(domainQueryParam.getPageNum(), domainQueryParam.getPageSize());
        List<DomainVO> domainVOList = domainRepository.queryDomainList(domainQueryVO);
        List<DomainDTO> domainDTOList = IDomainAssembler.IMPL.voListToDtoList(domainVOList);
        PageInfo pageInfo = new PageInfo(domainDTOList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

}
