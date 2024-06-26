package net.somta.juggle.console.infrastructure.converter;

import net.somta.juggle.console.domain.suite.api.vo.HeaderVO;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
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

    List<InputParameterVO> inputParamerterPoListToVoList(List<ParameterPO> inputParameterPoList);

    List<OutputParameterVO> outputParamerterPoListToVoList(List<ParameterPO> outputParameterPoList);

    default List<HeaderVO> headerParamerterPoListToVoList(List<ParameterPO> headerPoList){
        if ( headerPoList == null ) {
            return null;
        }

        List<HeaderVO> list = new ArrayList<HeaderVO>( headerPoList.size() );
        for ( ParameterPO parameterPo : headerPoList ) {
            HeaderVO headerVo = new HeaderVO();
            headerVo.setHeaderKey(parameterPo.getParamKey());
            headerVo.setHeaderName(parameterPo.getParamName());
            headerVo.setDataType(parameterPo.getDataType());
            headerVo.setRequired(parameterPo.getRequired());
            list.add(headerVo);
        }
        return list;
    }
}
