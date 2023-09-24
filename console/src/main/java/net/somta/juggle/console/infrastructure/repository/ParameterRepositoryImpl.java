package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gavin
 */
@Component
public class ParameterRepositoryImpl implements IParameterRepository {

    private final ParameterMapper parameterMapper;

    public ParameterRepositoryImpl(ParameterMapper parameterMapper) {
        this.parameterMapper = parameterMapper;
    }

    @Override
    public ParameterEntity getParameter(ParameterVO parameterQueryVO) {
        ParameterEntity parameterEntity = new ParameterEntity();
        List<ParameterPO> parameterPOList = parameterMapper.getParameterListByVO(parameterQueryVO);
        List<ParameterPO> inputParameterPOList = parameterPOList.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
        parameterEntity.setInputParameterList(inputParameterPOList);

        List<ParameterPO> outputParameterPOList = parameterPOList.stream()
                .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
        parameterEntity.setOutputParameterList(outputParameterPOList);
        return parameterEntity;
    }
}
