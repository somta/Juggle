package net.somta.juggle.console.infrastructure.converter;

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
            inputParameterVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(), DataType.class));
            inputParameterVo.setRequired(parameterPo.getRequired());
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
            outputParameterVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(), DataType.class));
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
            headerVo.setDataType(JsonSerializeHelper.deserialize(parameterPo.getDataType(),DataType.class));
            headerVo.setRequired(parameterPo.getRequired());
            list.add(headerVo);
        }
        return list;
    }
}
