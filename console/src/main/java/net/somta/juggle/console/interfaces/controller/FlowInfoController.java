package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.infrastructure.po.FlowInfoPO;
import net.somta.juggle.console.interfaces.param.FlowPageParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.console.application.service.IFlowInfoService;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.*;

/**
 * @author husong
 */
@Tag(name = "流程信息接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow")
public class FlowInfoController {

    @Autowired
    private IFlowInfoService flowInfoService;


    @Operation(summary = "删除流程")
    @DeleteMapping("/delete/{flowId}")
    public ResponseDataResult<Boolean> deleteFlowInfo(@PathVariable Long flowId){
        Boolean result = flowInfoService.deleteFlowInfo(flowId);
        return ResponseDataResult.setResponseResult(result);
    }

    @Operation(summary = "查询流程分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowDefinitionInfoPO> getFlowDefinitionList(@RequestBody FlowPageParam flowPageParam){
        return flowInfoService.queryByPageList(flowPageParam.getPageNum(),flowPageParam.getPageSize(), flowPageParam);
    }

    /**
     * 触发流程
     * @param triggerData 触发流程实体
     * @return Boolean
     */
    /*@Operation(summary = "触发流程")
    @PostMapping("/trigger/{flowKey}")
    public ResponseDataResult<FlowResult> triggerFlow(@PathVariable String flowKey, @RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(flowKey)){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowInfoPO flowInfoPO = flowInfoService.getFlowByFlowKey(flowKey);
        if(flowInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        //todo 这个方法要移动到流程版本下
        *//*if(FlowStatusEnum.DISABLED.getCode().equals(flowInfoPO.getFlowStatus())){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_ENABLE);
        }*//*

        FlowResult rst = flowInfoService.triggerFlow(flowInfoPO,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }*/



}
