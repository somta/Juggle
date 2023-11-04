package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.converter.IVariableInfoConverter;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Override
    public Boolean addVariable(VariableInfoEntity variableInfoEntity) {
        VariableInfoPO variableInfoPO = IVariableInfoConverter.IMPL.entityToPo(variableInfoEntity);
        variableInfoPO.setCreatedAt(new Date());
        variableInfoMapper.add(variableInfoPO);
        return true;
    }

    @Override
    public Boolean deleteVariableById(Long variableId) {
        variableInfoMapper.deleteById(variableId);
        return true;
    }

    @Override
    public Boolean updateVariable(Long variableId,VariableInfoEntity variableInfoEntity) {
        VariableInfoPO variableInfoPO = IVariableInfoConverter.IMPL.entityToPo(variableInfoEntity);
        variableInfoPO.setId(variableId);
        variableInfoMapper.update(variableInfoPO);
        return true;
    }

    @Override
    public List<VariableInfoVO> queryVariableInfoList(Long flowDefinitionId) {
        List<VariableInfoPO> variableInfoPOList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionId);
        List<VariableInfoVO> variableInfoVOList = IVariableInfoConverter.IMPL.poListToVoList(variableInfoPOList);
        return variableInfoVOList;
    }
}
