package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.console.service.IVariableInfoService;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "流程接口")
@RestController
@RequestMapping("/v1/flow")
public class FlowController {

    @Autowired
    private IFlowService flowService;
    @Autowired
    private IFlowDefinitionService flowDefinitionService;
    @Autowired
    private IVariableInfoService variableService;

    /**
     * 触发流程
     * @param triggerData 触发流程实体
     * @return Boolean
     */
    @PostMapping("/triggerFlow")
    public Boolean triggerFlow(@RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(triggerData.getFlowKey())){
            System.out.println("抛出异常");
        }
        // todo 如果有单独的流程实体类，直接查询对应的已经发布的流程表就可以了
        FlowDefinition flowDefinition = flowDefinitionService.getFlowDefinitionByKey(triggerData.getFlowKey());
        List<Variable> variables = variableService.getFlowVariableList(flowDefinition.getId());
        return flowService.triggerFlow(flowDefinition,variables,triggerData);
    }

}
