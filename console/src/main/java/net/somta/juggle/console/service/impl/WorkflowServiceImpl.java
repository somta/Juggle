package net.somta.juggle.console.service.impl;

import net.somta.juggle.core.enums.DataTypeEnum;
import net.somta.juggle.core.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowService;
import net.somta.juggle.core.dispatcher.IDispatcher;
import net.somta.juggle.core.dispatcher.impl.DefaultDispatcher;
import net.somta.juggle.core.model.Variable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkflowServiceImpl implements IWorkflowService {
    @Override
    public Boolean startFlow(WorkflowDefinition workflowDefinition) {
        IDispatcher dispatcher = new DefaultDispatcher();

        //todo mock一些变量数据
        List<Variable> variables = new ArrayList<>();
        Variable variable = new Variable();
        variable.setKey("env_id");
        variable.setName("用户ID");
        variable.setDataType(DataTypeEnum.Integer);



        dispatcher.send(workflowDefinition,variables);
        return null;
    }
}
