package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.common.identity.IdentityContext;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
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

    List<VariableInfoVO> poListToVoList(List<VariableInfoPO> variableInfoPoList);

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
            variableInfoPo.setDataType(variableInfoVo.getDataType());
            variableInfoPo.setFlowDefinitionId(flowDefinitionId);
            variableInfoPo.setCreatedAt(currentDate);
            variableInfoPo.setCreatedBy(IdentityContext.getIdentity().getUserId());
            list.add(variableInfoPo);
        }
        return list;
    }
}
