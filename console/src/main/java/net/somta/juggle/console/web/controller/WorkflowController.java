package net.somta.juggle.console.web.controller;

import net.somta.juggle.console.model.WorkflowDefinition;
import net.somta.juggle.console.service.IWorkflowDefinitionService;
import net.somta.juggle.console.service.IWorkflowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {

    @Autowired
    private IWorkflowService workflowService;
    @Autowired
    private IWorkflowDefinitionService workflowDefinitionService;

    /**
     * 触发流程
     * @param flowKey 流程Key
     * @return Boolean
     */
    @GetMapping("/triggerFlow")
    public Boolean triggerFlow(String flowKey){
        if(StringUtils.isEmpty(flowKey)){
            System.out.println("抛出异常");
        }
        WorkflowDefinition workflowDefinition = workflowDefinitionService.getFlowDefinitionByKey(flowKey);
        return workflowService.startFlow(workflowDefinition);
    }

}
