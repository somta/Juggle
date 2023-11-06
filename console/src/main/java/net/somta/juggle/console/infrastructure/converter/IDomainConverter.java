package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.domain.DomainEntity;
import net.somta.juggle.console.domain.domain.vo.DomainVO;
import net.somta.juggle.console.infrastructure.po.DomainPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IDomainConverter {
    IDomainConverter IMPL = Mappers.getMapper(IDomainConverter.class);

    DomainPO entityToPo(DomainEntity domainEntity);

    List<DomainVO> poListToVoList(List<DomainPO> domainPoList);
}
