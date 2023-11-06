package net.somta.juggle.console.application.service;

import net.somta.juggle.console.interfaces.dto.VariableInfoDTO;
import net.somta.juggle.console.interfaces.param.VariableAddParam;
import net.somta.juggle.console.interfaces.param.VariableUpdateParam;

import java.util.List;

/**
 * @author husong
 */
public interface IVariableInfoService {


    /**
     * 新增变量
     * @param variableAddParam
     */
    Boolean addVariable(VariableAddParam variableAddParam);

    /**
     * 删除变量
     * @param flowDefinitionId
     * @param variableId
     * @return
     */
    Boolean deleteVariable(Long flowDefinitionId, Long variableId);

    /**
     * 修改变量
     * @param variableUpdateParam
     * @return
     */
    Boolean updateVariable(VariableUpdateParam variableUpdateParam);

    /**
     * 获取流程变量列表
     * @param flowDefinitionId
     * @return
     */
    List<VariableInfoDTO> getVariableInfoList(Long flowDefinitionId);
}
