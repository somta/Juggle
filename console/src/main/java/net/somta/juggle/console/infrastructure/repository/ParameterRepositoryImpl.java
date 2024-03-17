/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.parameter.ParameterEntity;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.repository.IParameterRepository;
import net.somta.juggle.console.domain.parameter.vo.InputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.OutputParameterVO;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.converter.IParameterConverter;
import net.somta.juggle.console.infrastructure.mapper.ParameterMapper;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author husong
 * @since 1.0.0
 */
@Repository
public class ParameterRepositoryImpl implements IParameterRepository {

    private final ParameterMapper parameterMapper;

    public ParameterRepositoryImpl(ParameterMapper parameterMapper) {
        this.parameterMapper = parameterMapper;
    }

    @Override
    public ParameterEntity getParameter(ParameterVO parameterQueryVO) {
        ParameterEntity parameterEntity = new ParameterEntity();
        List<ParameterPO> parameterPoList = parameterMapper.getParameterListByVO(parameterQueryVO);
        List<ParameterPO> inputParameterPoList = parameterPoList.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
        List<InputParameterVO> inputParameterVoList = IParameterConverter.IMPL.inputParamerterPoListToVoList(inputParameterPoList);
        parameterEntity.setInputParameterList(inputParameterVoList);

        List<ParameterPO> outputParameterPoList = parameterPoList.stream()
                .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType()).collect(Collectors.toList());
        List<OutputParameterVO> outputParameterVoList = IParameterConverter.IMPL.outputParamerterPoListToVoList(outputParameterPoList);
        parameterEntity.setOutputParameterList(outputParameterVoList);
        return parameterEntity;
    }
}
