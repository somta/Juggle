package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IVariableInfoConverter {

    IVariableInfoConverter IMPL = Mappers.getMapper(IVariableInfoConverter.class);

    @Mapping(target = "dataType", expression = "java(net.somta.core.helper.JsonSerializeHelper.serialize(variableInfoEntity.getDataType()))")
    @Mapping(target = "envType", expression = "java(variableInfoEntity.getEnvType().getCode())")
    VariableInfoPO entityToPo(VariableInfoEntity variableInfoEntity);

    List<VariableInfoVO> poListToVoList(List<VariableInfoPO> variableInfoPoList);
}
