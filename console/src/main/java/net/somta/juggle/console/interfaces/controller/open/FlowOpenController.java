package net.somta.juggle.console.interfaces.controller.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.application.service.flow.IFlowRuntimeService;
import net.somta.juggle.console.application.service.flow.IFlowVersionService;
import net.somta.juggle.console.domain.flow.version.FlowVersionAO;
import net.somta.juggle.console.domain.flow.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.common.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_OPEN_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.flow.flowinfo.enums.FlowErrorEnum.FLOW_KEY_IS_EMPTY;
import static net.somta.juggle.console.domain.flow.flowinfo.enums.FlowErrorEnum.FLOW_NOT_EXIST;
import static net.somta.juggle.console.domain.flow.version.enums.FlowVersionErrorEnum.FLOW_NOT_ENABLE;

/**
 * @author husong
 */
@Tag(name = "流程开放接口")
@RestController
@RequestMapping(JUGGLE_OPEN_API_PREFIX + "/flow/")
public class FlowOpenController {

    private final IFlowVersionService flowVersionService;
    private final IFlowRuntimeService flowRuntimeService;

    public FlowOpenController(IFlowVersionService flowVersionService, IFlowRuntimeService flowRuntimeService) {
        this.flowVersionService = flowVersionService;
        this.flowRuntimeService = flowRuntimeService;
    }

    /**
     * 触发单参数流程
     * @param flowData trigger flow data
     * @return Boolean
     */
    @Operation(summary = "触发单参数流程")
    @GetMapping("/trigger/{flowVersion}/{flowKey}")
    public ResponseDataResult<FlowResult> triggerFlow(@PathVariable String flowVersion, @PathVariable String flowKey, @RequestParam Map<String,Object> flowData){
        if(StringUtils.isEmpty(flowKey)){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowVersionAO flowVersionAo = flowVersionService.getFlowVersionInfoByKey(flowKey,flowVersion);
        if(flowVersionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        if(FlowVersionStatusEnum.DISABLED == flowVersionAo.getFlowVersionStatusEnum()){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_ENABLE);
        }
        TriggerDataParam triggerData = new TriggerDataParam();
        triggerData.setFlowData(flowData);
        FlowResult rst = flowVersionService.triggerFlow(flowVersionAo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }

    /**
     * 触发流程
     * @param triggerData trigger flow data
     * @return Boolean
     */
    @Operation(summary = "触发流程")
    @PostMapping("/trigger/{flowVersion}/{flowKey}")
    public ResponseDataResult<FlowResult> triggerFlow(@PathVariable String flowVersion, @PathVariable String flowKey, @RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(flowKey)){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowVersionAO flowVersionAo = flowVersionService.getFlowVersionInfoByKey(flowKey,flowVersion);
        if(flowVersionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        if(FlowVersionStatusEnum.DISABLED == flowVersionAo.getFlowVersionStatusEnum()){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_ENABLE);
        }
        FlowResult rst = flowVersionService.triggerFlow(flowVersionAo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }


    @Operation(summary = "获取异步流程结果")
    @GetMapping("/getAsyncFlowResult/{flowInstanceId}")
    public ResponseDataResult<Map<String,Object>> getAsyncFlowResult(@PathVariable String flowInstanceId){
        Map<String,Object> flowResult = flowRuntimeService.getAsyncFlowResult(flowInstanceId);
        return ResponseDataResult.setResponseResult(flowResult);
    }

}
