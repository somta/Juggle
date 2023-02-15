package net.somta.juggle.console.service.impl;

import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.DataTypeInfo;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowServiceImpl implements IFlowService {

    private final IDispatcher dispatcher = new DefaultDispatcher();

    @Override
    public Boolean startFlow(FlowDefinition workflowDefinition) {

        //todo mock一些变量数据
        List<Variable> variables = new ArrayList<>();
        Variable variable = new Variable();
        variable.setKey("env_id");
        variable.setName("用户ID变量");
        variable.setDataType(new DataTypeInfo(DataTypeEnum.Integer));
        variable.setDefaultValue("1");

        dispatcher.send(workflowDefinition,variables);
        return null;
    }
}
