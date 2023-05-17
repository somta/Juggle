package net.somta.juggle.console.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.FlowInfo;
import net.somta.juggle.console.model.dto.FlowResultDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.enums.FlowDefinitionErrorEnum.FLOW_PARAM_ERROR;
import static net.somta.juggle.console.enums.FlowErrorEnum.FLOW_NOT_EXIST;

@Tag(name = "流程接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow")
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
    @Operation(summary = "触发流程")
    @PostMapping("/triggerFlow")
    public ResponseDataResult<FlowResultDTO> triggerFlow(@RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(triggerData.getFlowKey())){
            System.out.println("抛出异常");
        }
        FlowInfo flowInfo = flowService.getFlowByFlowKey(triggerData.getFlowKey());
        if(flowInfo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }

        FlowResultDTO rst = flowService.triggerFlow(flowInfo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }


    @Operation(summary = "获取异步流程结果")
    @GetMapping("/getAsyncFlowResult/{flowInstanceId}")
    public ResponseDataResult<Map<String,Object>> getAsyncFlowResult(@PathVariable String flowInstanceId){
        Map<String,Object> flowResult = flowService.getAsyncFlowResult(flowInstanceId);
        return ResponseDataResult.setResponseResult(flowResult);
    }

}
