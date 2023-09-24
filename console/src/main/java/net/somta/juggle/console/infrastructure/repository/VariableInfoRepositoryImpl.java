package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gavin
 */
@Component
public class VariableInfoRepositoryImpl implements IVariableInfoRepository {

    private final VariableInfoMapper variableInfoMapper;

    public VariableInfoRepositoryImpl(VariableInfoMapper variableInfoMapper) {
        this.variableInfoMapper = variableInfoMapper;
    }

    @Override
    public VariableInfoEntity queryVariableInfo(Long flowDefinitionId) {
        VariableInfoEntity variableInfoEntity = new VariableInfoEntity();
        List<VariableInfoPO> variableInfoPOList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionId);
        variableInfoEntity.setVariableInfoList(variableInfoPOList);
        return variableInfoEntity;
    }
}
