package net.somta.juggle.console.application.service.impl;

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
    public ResponsePaginationDataResult<List<DomainDTO>> getDomainPageList(DomainQueryParam domainQueryParam) {
        DomainQueryVO domainQueryVO = IDomainAssembler.IMPL.paramToVo(domainQueryParam);

        // todo 这里的分页查询是有问题的，要不要引用工具类呢
        Long count = domainRepository.queryDomainListCount(domainQueryVO);
        if (count > 0L) {
            List<DomainVO> domainVOList = domainRepository.queryDomainList(domainQueryVO);
            List<DomainDTO> domainDTOList = IDomainAssembler.IMPL.voListToDtoList(domainVOList);
            return ResponsePaginationDataResult.setPaginationDataResult(count, domainDTOList);
        } else {
            return ResponsePaginationDataResult.setPaginationDataResult(0L, null);
        }
    }

}
