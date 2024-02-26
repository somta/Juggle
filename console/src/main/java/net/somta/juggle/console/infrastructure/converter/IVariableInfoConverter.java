package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IVariableInfoConverter {

    IVariableInfoConverter IMPL = Mappers.getMapper(IVariableInfoConverter.class);

    List<VariableInfoVO> poListToVoList(List<VariableInfoPO> variableInfoPoList);

    List<VariableInfoPO> voListToPoList(List<VariableInfoVO> variableInfoVoList);
}
