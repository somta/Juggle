package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.flow.enums.FlowStatusEnum;
import net.somta.juggle.console.infrastructure.model.FlowDefinitionInfo;
import net.somta.juggle.console.infrastructure.model.FlowInfo;
import net.somta.juggle.console.interfaces.param.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.FlowPageParam;
import net.somta.juggle.console.interfaces.param.FlowStatusParam;
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
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.*;

@Tag(name = "流程接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow")
public class FlowController {

    @Autowired
    private IFlowService flowService;

    @Operation(summary = "启用或禁用流程")
    @PutMapping("/status")
    public ResponseDataResult<Boolean> updateFlowStatus(@RequestBody FlowStatusParam flowStatusParam){
        FlowInfo flowInfo = flowService.queryById(flowStatusParam.getFlowId());
        if(flowInfo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        if(FlowStatusEnum.DISABLED.getCode().equals(flowStatusParam.getFlowStatus())){
            flowInfo.setFlowStatus(FlowStatusEnum.ENABLE.getCode());
        }else {
            flowInfo.setFlowStatus(FlowStatusEnum.DISABLED.getCode());
        }
        return flowService.update(flowInfo);
    }

    @Operation(summary = "删除流程")
    @DeleteMapping("/delete/{flowId}")
    public ResponseDataResult<Boolean> deleteFlow(@PathVariable Long flowId){
        FlowInfo flowInfo = flowService.queryById(flowId);
        if(FlowStatusEnum.ENABLE.getCode().equals(flowInfo.getFlowStatus())){
            return ResponseDataResult.setErrorResponseResult(ENABLE_FLOW_NOT_DELETE);
        }
        return flowService.deleteById(flowId);
    }

    @Operation(summary = "查询流程分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowDefinitionInfo> getFlowDefinitionList(@RequestBody FlowPageParam flowPageParam){
        return flowService.queryByPageList(flowPageParam.getPageNum(),flowPageParam.getPageSize(), flowPageParam);
    }

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
        if(FlowStatusEnum.DISABLED.getCode().equals(flowInfo.getFlowStatus())){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_ENABLE);
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
