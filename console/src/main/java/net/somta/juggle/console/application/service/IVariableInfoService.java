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
     * Add variables
     * @param variableAddParam Add parameter objects for variables
     * @return Boolean
     */
    Boolean addVariable(VariableAddParam variableAddParam);

    /**
     * Delete variables based on flow definition ID and variable ID
     * @param flowDefinitionId flow definition id
     * @param variableId variable id
     * @return Boolean
     */
    Boolean deleteVariable(Long flowDefinitionId, Long variableId);

    /**
     * Update variables
     * @param variableUpdateParam Update variable parameter objects
     * @return Boolean
     */
    Boolean updateVariable(VariableUpdateParam variableUpdateParam);

    /**
     * Query flow variable information list
     * @param flowDefinitionId flow definition id
     * @return Variable information list
     */
    List<VariableInfoDTO> getVariableInfoList(Long flowDefinitionId);
}
