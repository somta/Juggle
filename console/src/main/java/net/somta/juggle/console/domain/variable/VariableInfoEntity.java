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

    /**
     * 流程定义ID
     */
    private Long flowDefinitionId;

    /**
     * 变量Key,同一流程内唯一
     */
    private String envKey;

    /**
     * variable name
     */
    private String envName;

    /**
     * variable type 1：入参变量   2：出参变量  3：中间变量
     */
    private VariableTypeEnum envType;

    /**
     * data type
     */
    private DataType dataType;

    private List<VariableInfoPO> variableInfoPOList;

    public Long getFlowDefinitionId() {
        return flowDefinitionId;
    }

    public void setFlowDefinitionId(Long flowDefinitionId) {
        this.flowDefinitionId = flowDefinitionId;
    }

    public String getEnvKey() {
        return envKey;
    }

    public void setEnvKey(String envKey) {
        this.envKey = envKey;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public VariableTypeEnum getEnvType() {
        return envType;
    }

    public void setEnvType(VariableTypeEnum envType) {
        this.envType = envType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

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
