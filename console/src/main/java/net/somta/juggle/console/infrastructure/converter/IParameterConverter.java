package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IParameterConverter {

    IParameterConverter IMPL = Mappers.getMapper(IParameterConverter.class);

    List<InputParameterVO> inputParamerterPoListToVoList(List<ParameterPO> inputParameterPOList);

    List<OutputParameterVO> outputParamerterPoListToVoList(List<ParameterPO> outputParameterPOList);
}
