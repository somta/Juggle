package net.somta.juggle.console.service;

import net.somta.juggle.console.model.VariableInfo;
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
     * @param envId
     * @return
     */
    Boolean deleteVariable(Long flowDefinitionId, Long envId);

    /**
     * 修改变量
     * @param variableInfo
     * @return
     */
    Boolean updateVariable(VariableInfo variableInfo);

    List<Variable> getFlowVariableList(Integer flowDefinitionId);



}
