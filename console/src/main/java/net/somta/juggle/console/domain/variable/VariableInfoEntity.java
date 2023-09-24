package net.somta.juggle.console.domain.variable;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gavin
 */
public class VariableInfoEntity {

    private List<VariableInfoPO> variableInfoPOList;

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

    public void setVariableInfoList(List<VariableInfoPO> variableInfoPOList) {
        this.variableInfoPOList = variableInfoPOList;
    }
}
