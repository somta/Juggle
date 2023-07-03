package net.somta.juggle.console.application.service;

import net.somta.juggle.console.infrastructure.model.VariableInfo;
import net.somta.juggle.core.model.Variable;

import java.util.List;

public interface IVariableInfoService {


    /**
     * 新增变量
     * @param variableInfo
     */
    Boolean addVariable(VariableInfo variableInfo);

    /**
     * 删除变量
     * @param flowDefinitionId
     * @param variableId
     * @return
     */
    Boolean deleteVariable(Long flowDefinitionId, Long variableId);

    /**
     * 修改变量
     * @param variableInfo
     * @return
     */
    Boolean updateVariable(VariableInfo variableInfo);

    /**
     * 获取流程变量列表
     * @param flowDefinitionId
     * @return
     */
    List<Variable> getFlowVariableList(Long flowDefinitionId);



}
