package net.somta.juggle.console.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.param.TriggerDataParam;
import net.somta.juggle.console.service.IVariableInfoService;
import net.somta.juggle.core.model.FlowDefinition;
import net.somta.juggle.console.service.IFlowDefinitionService;
import net.somta.juggle.console.service.IFlowService;
import net.somta.juggle.core.model.InputParameter;
import net.somta.juggle.core.model.OutputParameter;
import net.somta.juggle.core.model.Variable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.somta.juggle.console.enums.FlowDefinitionErrorEnum.FLOW_PARAM_ERROR;
import static net.somta.juggle.console.enums.FlowErrorEnum.FLOW_NOT_EXIST;

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
    public ResponseDataResult<Boolean> triggerFlow(@RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(triggerData.getFlowKey())){
            System.out.println("抛出异常");
        }
        FlowInfo flowInfo = flowService.getFlowByFlowKey(triggerData.getFlowKey());
        if(flowInfo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }

        /*FlowDefinition flowDefinition = flowDefinitionService.getFlowDefinitionByKey(triggerData.getFlowKey());
        List<Variable> variables = variableService.getFlowVariableList(flowDefinition.getId());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String var = objectMapper.writeValueAsString(variables);
            System.out.println(var);

            List<InputParameter> inputParameters = flowDefinition.getInputParameters();
            String inputs = objectMapper.writeValueAsString(inputParameters);
            System.out.println(inputs);

            List<OutputParameter> outputParameters = flowDefinition.getOutputParameters();
            String outputs = objectMapper.writeValueAsString(outputParameters);
            System.out.println(outputs);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        Boolean rst = flowService.triggerFlow(flowInfo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }

}
