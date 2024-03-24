package net.somta.juggle.console.application.assembler.system;

import net.somta.juggle.console.domain.system.token.vo.TokenVO;
import net.somta.juggle.console.interfaces.dto.TokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface ITokenAssembler {

    ITokenAssembler IMPL = Mappers.getMapper(ITokenAssembler.class);

    List<TokenDTO> voListToDtoList(List<TokenVO> tokenVoList);
}
