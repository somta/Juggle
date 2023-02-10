package net.somta.juggle.console.web.controller;

import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.console.service.IFlowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowController {

    @Autowired
    private IFlowService flowService;
    @Autowired
    private IFlowDefinitionService flowDefinitionService;

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
        FlowDefinition flowDefinition = flowDefinitionService.getFlowDefinitionByKey(flowKey);
        return flowService.startFlow(flowDefinition);
    }

}
