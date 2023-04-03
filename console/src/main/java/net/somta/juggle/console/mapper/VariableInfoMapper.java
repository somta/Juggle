package net.somta.juggle.console.mapper;

import net.somta.juggle.console.model.VariableInfo;

import java.util.Map;

public interface VariableInfoMapper {
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


}
