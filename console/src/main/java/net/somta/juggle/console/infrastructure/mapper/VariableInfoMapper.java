package net.somta.juggle.console.infrastructure.mapper;

import net.somta.juggle.console.infrastructure.po.VariableInfoPO;

import java.util.List;
import java.util.Map;

public interface VariableInfoMapper {
    /**
     * 新增变量
     * @param variableInfoPO
     * @return
     */
    int addVariable(VariableInfoPO variableInfoPO);

    /**
     * 批量新增变量
     * @param variableInfoPOList
     * @return
     */
    int batchAddVariable(List<VariableInfoPO> variableInfoPOList);

    /**
     * 更新变量
     * @param variableInfoPO
     * @return
     */
    int updateVariable(VariableInfoPO variableInfoPO);

    /**
     * 根据流程定义ID删除变量
     * @param flowDefinitionId
     * @return
     */
    int deleteVariableByFlowDefinitionId(Long flowDefinitionId);

    /**
     * 根据流程定义ID和变量ID删除变量
     * @param parms
     * @return
     */
    int deleteVariable(Map<String, Long> parms);

    /**
     *
     * @param flowDefinitionId
     * @return
     */
    List<VariableInfoPO> queryVariableInfoListByDefinitionId(Long flowDefinitionId);
}
