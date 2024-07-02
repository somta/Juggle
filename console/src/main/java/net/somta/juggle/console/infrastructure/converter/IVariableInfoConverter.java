package net.somta.juggle.console.infrastructure.converter;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.core.model.DataType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IVariableInfoConverter {

    IVariableInfoConverter IMPL = Mappers.getMapper(IVariableInfoConverter.class);

    default List<VariableInfoVO> poListToVoList(List<VariableInfoPO> variableInfoPoList){
        if (variableInfoPoList == null) {
            return null;
        }
        List<VariableInfoVO> list = new ArrayList<>(variableInfoPoList.size());
        VariableInfoVO variableInfoVo = null;
        for (VariableInfoPO variableInfoPo : variableInfoPoList){
            variableInfoVo = new VariableInfoVO();
            variableInfoVo.setId(variableInfoPo.getId());
            variableInfoVo.setEnvKey(variableInfoPo.getEnvKey());
            variableInfoVo.setEnvName(variableInfoPo.getEnvName());
            variableInfoVo.setDataType(JsonSerializeHelper.deserialize(variableInfoPo.getDataType(), DataType.class));
            variableInfoVo.setEnvType(variableInfoPo.getEnvType());
            list.add(variableInfoVo);
        }
        return list;
    }

    default List<VariableInfoPO> voListToPoList(List<VariableInfoVO> variableInfoVoList,Long flowDefinitionId){
        if ( variableInfoVoList == null ) {
            return null;
        }
        List<VariableInfoPO> list = new ArrayList<>(variableInfoVoList.size());
        VariableInfoPO variableInfoPo = null;
        Date currentDate = new Date();
        for (VariableInfoVO variableInfoVo : variableInfoVoList) {
            variableInfoPo = new VariableInfoPO();
            variableInfoPo.setEnvKey(variableInfoVo.getEnvKey());
            variableInfoPo.setEnvName(variableInfoVo.getEnvName());
            variableInfoPo.setEnvType(variableInfoVo.getEnvType());
            variableInfoPo.setDataType(JsonSerializeHelper.serialize(variableInfoVo.getDataType()));
            variableInfoPo.setFlowDefinitionId(flowDefinitionId);
            variableInfoPo.setCreatedAt(currentDate);
            variableInfoPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
            list.add(variableInfoPo);
        }
        return list;
    }
}
