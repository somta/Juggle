package net.somta.juggle.console.domain.variable;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.variable.enums.VariableTypeEnum;
import net.somta.juggle.console.infrastructure.po.ParameterPO;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author husong
 */
public class VariableInfoEntity {

    private List<VariableInfoPO> variableInfoPOList;

    /**
     * 获取运行时的变量列表
     * @return
     */
    public List<Variable> getFlowRuntimeVariables(){
        List<Variable> variables = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(this.variableInfoPOList)){
            for (VariableInfoPO variableInfoPO : variableInfoPOList) {
                Variable variable = new Variable();
                variable.setKey(variableInfoPO.getEnvKey());
                variable.setName(variableInfoPO.getEnvName());
                variable.setDataType(JsonSerializeHelper.deserialize(variableInfoPO.getDataType(),DataType.class));
                variables.add(variable);
            }
        }
        return variables;
    }

    /**
     * 将出入参数转换成变量
     * @param parameters
     * @param flowDefinitionId
     */
    public void convertParameterToVariables(List<ParameterPO> parameters,Long flowDefinitionId){
        if(CollectionUtils.isNotEmpty(parameters)){
            for (ParameterPO parameterPO : parameters) {
                VariableInfoPO variableInfoPO = new VariableInfoPO();
                variableInfoPO.setEnvKey(parameterPO.getParamKey());
                variableInfoPO.setEnvName(parameterPO.getParamName());
                variableInfoPO.setEnvType(parameterPO.getParamType() == ParameterTypeEnum.INPUT_PARAM.getCode() ? VariableTypeEnum.INPUT_PARAM_VARIABLE.getCode()
                        : VariableTypeEnum.OUTPUT_PARAM_VARIABLE.getCode());
                variableInfoPO.setDataType(parameterPO.getDataType());
                variableInfoPO.setFlowDefinitionId(flowDefinitionId);
                variableInfoPO.setCreatedAt(new Date());
                variableInfoPOList.add(variableInfoPO);
            }
        }
    }

    public void setVariableInfoList(List<VariableInfoPO> variableInfoPOList) {
        this.variableInfoPOList = variableInfoPOList;
    }
}
