package net.somta.juggle.console.service.impl;

import net.somta.juggle.console.mapper.VariableInfoMapper;
import net.somta.juggle.console.model.VariableInfo;
import net.somta.juggle.console.service.IVariableInfoService;
import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataType;
import net.somta.juggle.core.model.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VariableInfoServiceImpl implements IVariableInfoService {

    @Autowired
    private VariableInfoMapper variableInfoMapper;

    @Override
    public Boolean addVariable(VariableInfo variableInfo) {
        // todo 这里要改成雪花ID
        variableInfo.setId(2L);
        variableInfoMapper.addVariable(variableInfo);
        return true;
    }

    @Override
    public Boolean deleteVariable(Long flowDefinitionId, Long envId) {
        Map<String,Long> parms = new HashMap<>();
        parms.put("flowDefinitionId",flowDefinitionId);
        parms.put("id",envId);
        variableInfoMapper.deleteVariable(parms);
        return true;
    }

    @Override
    public Boolean updateVariable(VariableInfo variableInfo) {
        variableInfoMapper.updateVariable(variableInfo);
        return true;
    }

    @Override
    public List<Variable> getFlowVariableList(Long flowDefinitionId) {
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
