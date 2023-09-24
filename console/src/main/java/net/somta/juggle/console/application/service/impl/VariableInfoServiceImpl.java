package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.infrastructure.mapper.VariableInfoMapper;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.console.application.service.IVariableInfoService;
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
    public Boolean addVariable(VariableInfoPO variableInfoPO) {
        // todo 这里要改成雪花ID
        variableInfoPO.setId(2L);
        variableInfoMapper.addVariable(variableInfoPO);
        return true;
    }

    @Override
    public Boolean deleteVariable(Long flowDefinitionId, Long variableId) {
        Map<String,Long> parms = new HashMap<>();
        parms.put("flowDefinitionId",flowDefinitionId);
        parms.put("id",variableId);
        variableInfoMapper.deleteVariable(parms);
        return true;
    }

    @Override
    public Boolean updateVariable(VariableInfoPO variableInfoPO) {
        variableInfoMapper.updateVariable(variableInfoPO);
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
