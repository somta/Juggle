package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.console.application.service.IVariableInfoService;
import net.somta.juggle.console.application.service.IFlowDefinitionService;
import net.somta.juggle.console.application.service.IFlowService;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.FLOW_KEY_IS_EMPTY;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.FLOW_NOT_EXIST;

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
    public ResponseDataResult<FlowResult> triggerFlow(@RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(triggerData.getFlowKey())){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowInfo flowInfo = flowService.getFlowByFlowKey(triggerData.getFlowKey());
        if(flowInfo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }

        FlowResult rst = flowService.triggerFlow(flowInfo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }


    @Operation(summary = "获取异步流程结果")
    @GetMapping("/getAsyncFlowResult/{flowInstanceId}")
    public ResponseDataResult<Map<String,Object>> getAsyncFlowResult(@PathVariable String flowInstanceId){
        Map<String,Object> flowResult = flowService.getAsyncFlowResult(flowInstanceId);
        return ResponseDataResult.setResponseResult(flowResult);
    }

}
