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
