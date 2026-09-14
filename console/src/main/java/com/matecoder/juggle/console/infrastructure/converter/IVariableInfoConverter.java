package com.matecoder.juggle.console.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.matecoder.common.utils.JsonUtil;
import com.matecoder.core.context.ApplicationContext;
import com.matecoder.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import com.matecoder.juggle.console.infrastructure.po.VariableInfoPO;
import com.matecoder.juggle.core.model.DataType;
import org.mapstruct.Mapper;
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
            variableInfoVo.setVariableKey(variableInfoPo.getVariableKey());
            variableInfoVo.setVariableName(variableInfoPo.getVariableName());
            try {
                variableInfoVo.setDataType(JsonUtil.deserialize(variableInfoPo.getDataType(), DataType.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            variableInfoVo.setVariableType(variableInfoPo.getVariableType());
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
            variableInfoPo.setVariableKey(variableInfoVo.getVariableKey());
            variableInfoPo.setVariableName(variableInfoVo.getVariableName());
            variableInfoPo.setVariableType(variableInfoVo.getVariableType());
            try {
                variableInfoPo.setDataType(JsonUtil.serialize(variableInfoVo.getDataType()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            variableInfoPo.setFlowDefinitionId(flowDefinitionId);
            variableInfoPo.setCreatedAt(currentDate);
            variableInfoPo.setCreatedBy(ApplicationContext.getIdentityContext().getUserId());
            list.add(variableInfoPo);
        }
        return list;
    }
}
