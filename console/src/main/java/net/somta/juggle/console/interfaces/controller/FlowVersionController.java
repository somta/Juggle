package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.application.service.IFlowInfoService;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.FlowPageParam;
import net.somta.juggle.console.interfaces.param.FlowStatusParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.*;

@Tag(name = "流程版本接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow/version/")
public class FlowVersionController {

    @Autowired
    private IFlowInfoService flowService;
    @Autowired
    private IFlowRuntimeService flowRuntimeService;

    @Operation(summary = "启用或禁用流程")
    @PutMapping("/status")
    public ResponseDataResult<Boolean> updateFlowStatus(@RequestBody FlowStatusParam flowStatusParam){
        FlowInfoPO flowInfoPO = flowService.queryById(flowStatusParam.getFlowVersionId());
        if(flowInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        /*if(FlowStatusEnum.DISABLED.getCode().equals(flowStatusParam.getFlowStatus())){
            flowInfoPO.setFlowStatus(FlowStatusEnum.ENABLE.getCode());
        }else {
            flowInfoPO.setFlowStatus(FlowStatusEnum.DISABLED.getCode());
        }*/
        return flowService.update(flowInfoPO);
    }

    @Operation(summary = "删除流程")
    @DeleteMapping("/delete/{flowId}")
    public ResponseDataResult<Boolean> deleteFlow(@PathVariable Long flowId){
        FlowInfoPO flowInfoPO = flowService.queryById(flowId);
        /*if(FlowStatusEnum.ENABLE.getCode().equals(flowInfoPO.getFlowStatus())){
            return ResponseDataResult.setErrorResponseResult(ENABLE_FLOW_NOT_DELETE);
        }*/
        return flowService.deleteById(flowId);
    }

    @Operation(summary = "查询流程分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowDefinitionInfoPO> getFlowDefinitionList(@RequestBody FlowPageParam flowPageParam){
        return flowService.queryByPageList(flowPageParam.getPageNum(),flowPageParam.getPageSize(), flowPageParam);
    }

    /**
     * 触发流程
     * @param triggerData 触发流程实体
     * @return Boolean
     */
    @Operation(summary = "触发流程")
    @PostMapping("/trigger/{flowKey}")
    public ResponseDataResult<FlowResult> triggerFlow(@PathVariable String flowKey, @RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(flowKey)){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowInfoPO flowInfoPO = flowService.getFlowByFlowKey(flowKey);
        if(flowInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        /*if(FlowStatusEnum.DISABLED.getCode().equals(flowInfoPO.getFlowStatus())){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_ENABLE);
        }*/

        FlowResult rst = flowService.triggerFlow(flowInfoPO,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }


    @Operation(summary = "获取异步流程结果")
    @GetMapping("/getAsyncFlowResult/{flowInstanceId}")
    public ResponseDataResult<Map<String,Object>> getAsyncFlowResult(@PathVariable String flowInstanceId){
        Map<String,Object> flowResult = flowRuntimeService.getAsyncFlowResult(flowInstanceId);
        return ResponseDataResult.setResponseResult(flowResult);
    }

}
