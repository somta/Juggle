package net.somta.juggle.console.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.core.model.DataType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
                inputParameterVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(), DataType.class));
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
                outputParameterVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(), DataType.class));
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
            headerVo.setHeaderKey(parameterPo.getParamKey());
            headerVo.setHeaderName(parameterPo.getParamName());
            try {
                headerVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(),DataType.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            headerVo.setRequired(parameterPo.getRequired());
            headerVo.setHeaderDesc(parameterPo.getParamDesc());
            list.add(headerVo);
        }
        return list;
    }
}
