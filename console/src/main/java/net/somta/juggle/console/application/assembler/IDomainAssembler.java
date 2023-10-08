package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.vo.DomainQueryVO;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IDomainAssembler {

    IDomainAssembler IMPL = Mappers.getMapper(IDomainAssembler.class);

    DomainEntity paramToEntity(DomainAddParam domainAddPara);

    DomainEntity paramToEntity(DomainUpdateParam domainUpdateParam);

    List<DomainDTO> voListToDtoList(List<DomainVO> domainVOList);

    DomainQueryVO paramToVo(DomainQueryParam domainQueryParam);
}
