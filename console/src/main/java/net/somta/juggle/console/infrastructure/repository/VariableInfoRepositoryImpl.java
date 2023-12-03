package net.somta.juggle.console.infrastructure.repository;

import net.somta.juggle.console.domain.variable.VariableInfoEntity;
import net.somta.juggle.console.domain.variable.repository.IVariableInfoRepository;
import net.somta.juggle.console.domain.variable.vo.VariableInfoVO;
import net.somta.juggle.console.infrastructure.converter.IVariableInfoConverter;
import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    public Boolean addVariable(VariableInfoEntity variableInfoEntity) {
        VariableInfoPO variableInfoPo = IVariableInfoConverter.IMPL.entityToPo(variableInfoEntity);
        variableInfoPo.setCreatedAt(new Date());
        variableInfoMapper.add(variableInfoPo);
        return true;
    }

    @Override
    public Boolean deleteVariableById(Long variableId) {
        variableInfoMapper.deleteById(variableId);
        return true;
    }

    @Override
    public Boolean updateVariable(Long variableId,VariableInfoEntity variableInfoEntity) {
        VariableInfoPO variableInfoPo = IVariableInfoConverter.IMPL.entityToPo(variableInfoEntity);
        variableInfoPo.setId(variableId);
        variableInfoMapper.update(variableInfoPo);
        return true;
    }

    @Override
    public List<VariableInfoVO> queryVariableInfoList(Long flowDefinitionId) {
        List<VariableInfoPO> variableInfoPoList = variableInfoMapper.queryVariableInfoListByDefinitionId(flowDefinitionId);
        List<VariableInfoVO> variableInfoVoList = IVariableInfoConverter.IMPL.poListToVoList(variableInfoPoList);
        return variableInfoVoList;
    }
}
