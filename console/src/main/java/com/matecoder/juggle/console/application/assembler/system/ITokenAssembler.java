package com.matecoder.juggle.console.application.assembler.system;

import com.matecoder.juggle.console.domain.system.token.vo.TokenVO;
import com.matecoder.juggle.console.interfaces.dto.system.TokenDTO;
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
