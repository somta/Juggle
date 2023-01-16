package net.somta.juggle.console.service.impl;

import net.somta.juggle.core.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowDefinitionService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowDefinitionServiceImpl implements IWorkflowDefinitionService {
    @Override
    public WorkflowDefinition getFlowDefinitionByKey(String flowKey) {
        //TODO 先mock一个流程定义数据
        WorkflowDefinition workflowDefinition = new WorkflowDefinition();
        workflowDefinition.setFlowKey("flow_123");
        workflowDefinition.setTenantId("66");
        workflowDefinition.setRemark("这是一个测试流程");

        String contentJson = "";
        workflowDefinition.setContent(contentJson);

        return workflowDefinition;
    }
}
