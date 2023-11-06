package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.interfaces.dto.VariableInfoDTO;
import net.somta.juggle.console.interfaces.param.VariableAddParam;
import net.somta.juggle.console.interfaces.param.VariableUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IVariableInfoAssembler {

    IVariableInfoAssembler IMPL = Mappers.getMapper(IVariableInfoAssembler.class);

    VariableInfoEntity paramToEntity(VariableAddParam variableAddParam);

    VariableInfoEntity paramToEntity(VariableUpdateParam variableUpdateParam);

    List<VariableInfoDTO> voListToDtoList(List<VariableInfoVO> variableInfoVOList);
}
