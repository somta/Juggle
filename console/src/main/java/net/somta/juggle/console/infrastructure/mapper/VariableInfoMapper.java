package net.somta.juggle.console.infrastructure.mapper;

import net.somta.core.base.IBaseMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;

import java.util.List;
import java.util.Map;

public interface VariableInfoMapper extends IBaseMapper {

    /**
     * 批量新增变量
     * @param variableInfoPOList
     * @return
     */
    int batchAddVariable(List<VariableInfoPO> variableInfoPOList);

    /**
     * 根据流程定义ID删除变量
     * @param flowDefinitionId
     * @return
     */
    int deleteVariableByFlowDefinitionId(Long flowDefinitionId);

    /**
     *
     * @param flowDefinitionId
     * @return
     */
    List<VariableInfoPO> queryVariableInfoListByDefinitionId(Long flowDefinitionId);
}
