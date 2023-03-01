package net.somta.juggle.console.web.controller;

import net.somta.juggle.console.model.TriggerData;
import net.somta.juggle.console.service.IVariableService;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlowController {

    @Autowired
    private IFlowService flowService;
    @Autowired
    private IFlowDefinitionService flowDefinitionService;
    @Autowired
    private IVariableService variableService;

    /**
     * 触发流程
     * @param triggerData 触发流程实体
     * @return Boolean
     */
    @PostMapping("/triggerFlow")
    public Boolean triggerFlow(TriggerData triggerData){
        if(StringUtils.isEmpty(triggerData.getFlowKey())){
            System.out.println("抛出异常");
        }
        FlowDefinition flowDefinition = flowDefinitionService.getFlowDefinitionByKey(triggerData.getFlowKey());
        List<Variable> variables = variableService.getFlowVariableList(flowDefinition.getId());
        return flowService.triggerFlow(flowDefinition,variables,triggerData);
    }

}
