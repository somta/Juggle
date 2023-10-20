package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.FlowInfoDTO;
import net.somta.juggle.console.interfaces.param.FlowInfoPageParam;
import net.somta.juggle.console.application.service.IFlowInfoService;
import org.springframework.web.bind.annotation.*;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "流程信息接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow")
public class FlowInfoController {

    private final IFlowInfoService flowInfoService;

    public FlowInfoController(IFlowInfoService flowInfoService) {
        this.flowInfoService = flowInfoService;
    }

    @Operation(summary = "删除流程")
    @DeleteMapping("/delete/{flowId}")
    public ResponseDataResult<Boolean> deleteFlowInfo(@PathVariable Long flowId){
        Boolean result = flowInfoService.deleteFlowInfo(flowId);
        return ResponseDataResult.setResponseResult(result);
    }

    @Operation(summary = "查询流程分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowInfoDTO> getFlowInfoPageList(@RequestBody FlowInfoPageParam flowInfoPageParam){
        PageInfo pageInfo = flowInfoService.getFlowInfoPageList(flowInfoPageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
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
