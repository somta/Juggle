package com.matecoder.juggle.console.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.matecoder.common.utils.JsonUtil;
import com.matecoder.juggle.console.domain.suite.api.vo.HeaderVO;
import com.matecoder.juggle.console.domain.parameter.vo.InputParameterVO;
import com.matecoder.juggle.console.domain.parameter.vo.OutputParameterVO;
import com.matecoder.juggle.console.infrastructure.po.ParameterPO;
import com.matecoder.juggle.core.model.DataType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author husong
 */
@Mapper
public interface IParameterConverter {

    IParameterConverter IMPL = Mappers.getMapper(IParameterConverter.class);

    default List<InputParameterVO> inputParamerterPoListToVoList(List<ParameterPO> inputParameterPoList){
        if(inputParameterPoList == null){
            return null;
        }
        List<InputParameterVO> list = new ArrayList<>(inputParameterPoList.size());
        for (ParameterPO parameterPo : inputParameterPoList) {
            InputParameterVO inputParameterVo = new InputParameterVO();
            inputParameterVo.setParamKey(parameterPo.getParamKey());
            inputParameterVo.setParamName(parameterPo.getParamName());
            inputParameterVo.setParamPosition(parameterPo.getParamPosition());
            try {
                inputParameterVo.setDataType(JsonUtil.deserialize(parameterPo.getDataType(), DataType.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            inputParameterVo.setRequired(parameterPo.getRequired());
            inputParameterVo.setParamDesc(parameterPo.getParamDesc());
            list.add(inputParameterVo);
        }
        return list;
    }

    default List<OutputParameterVO> outputParamerterPoListToVoList(List<ParameterPO> outputParameterPoList){
        if(outputParameterPoList == null){
            return null;
        }
        List<OutputParameterVO> list = new ArrayList<>(outputParameterPoList.size());
        for (ParameterPO parameterPo : outputParameterPoList) {
            OutputParameterVO outputParameterVo = new OutputParameterVO();
            outputParameterVo.setParamKey(parameterPo.getParamKey());
            outputParameterVo.setParamName(parameterPo.getParamName());
            try {
                outputParameterVo.setDataType(JsonUtil.deserialize(parameterPo.getDataType(), DataType.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            outputParameterVo.setParamDesc(parameterPo.getParamDesc());
            list.add(outputParameterVo);
        }
        return list;
    }

    default List<HeaderVO> headerParamerterPoListToVoList(List<ParameterPO> headerPoList){
        if ( headerPoList == null ) {
            return null;
        }

        List<HeaderVO> list = new ArrayList<HeaderVO>( headerPoList.size() );
        for ( ParameterPO parameterPo : headerPoList ) {
            HeaderVO headerVo = new HeaderVO();
            headerVo.setParamKey(parameterPo.getParamKey());
            headerVo.setParamName(parameterPo.getParamName());
            try {
                headerVo.setDataType(JsonUtil.deserialize(parameterPo.getDataType(),DataType.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            headerVo.setRequired(parameterPo.getRequired());
            headerVo.setParamDesc(parameterPo.getParamDesc());
            list.add(headerVo);
        }
        return list;
    }
}
