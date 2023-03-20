package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.service.IVariableInfoService;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariableInfoServiceImpl implements IVariableInfoService {
    @Override
    public void addVariable() {

    }

    @Override
    public List<Variable> getFlowVariableList(Integer flowDefinitionId) {
        //todo mock一些变量数据
        List<Variable> variables = new ArrayList<>();
        Variable variableId = new Variable();
        variableId.setKey("env_id");
        variableId.setName("入参-用户ID变量");
        variableId.setDataType(new DataType(DataTypeEnum.Integer));
        variableId.setDefaultValue("1");
        variables.add(variableId);

        Variable variableName = new Variable();
        variableName.setKey("env_name");
        variableName.setName("入参-用户姓名变量");
        variableName.setDataType(new DataType(DataTypeEnum.String));
        variables.add(variableName);


        Variable variableUserName = new Variable();
        variableUserName.setKey("env_userName");
        variableUserName.setName("流程出参-用户姓名变量");
        variableUserName.setDataType(new DataType(DataTypeEnum.String));
        variables.add(variableUserName);

        return variables;
    }
}
