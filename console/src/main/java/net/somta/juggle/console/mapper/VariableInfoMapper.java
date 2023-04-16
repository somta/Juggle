package net.somta.juggle.console.mapper;

import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.VariableInfo;

import java.util.List;
import java.util.Map;

public interface VariableInfoMapper {
    /**
     * 新增变量
     * @param variableInfo
     * @return
     */
    int addVariable(VariableInfo variableInfo);

    /**
     * 更新变量
     * @param variableInfo
     * @return
     */
    int updateVariable(VariableInfo variableInfo);

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
    List<VariableInfo> queryVariableInfoListByDefinitionId(Long flowDefinitionId);
}
