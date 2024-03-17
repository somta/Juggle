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

import net.somta.juggle.console.domain.flow.definition.repository.IVariableInfoRepository;
import net.somta.juggle.console.domain.flow.definition.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.converter.IVariableInfoConverter;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Gavin
 * @since 1.0.0
 */
@Repository
public class VariableInfoRepositoryImpl implements IVariableInfoRepository {

    private final VariableInfoMapper variableInfoMapper;

    public VariableInfoRepositoryImpl(VariableInfoMapper variableInfoMapper) {
        this.variableInfoMapper = variableInfoMapper;
    }

    @Override
    public List<VariableInfoVO> queryVariableInfoList(Long flowDefinitionId) {
        List<VariableInfoPO> variableInfoPoList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionId);
        List<VariableInfoVO> variableInfoVoList = IVariableInfoConverter.IMPL.poListToVoList(variableInfoPoList);
        return variableInfoVoList;
    }
}
