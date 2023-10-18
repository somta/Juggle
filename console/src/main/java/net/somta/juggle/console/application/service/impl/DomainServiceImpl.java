package net.somta.juggle.console.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.IDomainAssembler;
import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.repository.IDomainRepository;
import net.somta.juggle.console.domain.domain.vo.DomainQueryVO;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.infrastructure.po.DomainPO;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import net.somta.juggle.console.application.service.IDomainService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        List<DomainDTO> domainDTOList = IDomainAssembler.IMPL.voListToDtoList(domainVOList);
        return domainDTOList;
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
