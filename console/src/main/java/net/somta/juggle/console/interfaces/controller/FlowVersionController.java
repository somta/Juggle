package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IFlowRuntimeService;
import net.somta.juggle.console.application.service.IFlowVersionService;
import net.somta.juggle.console.domain.version.FlowVersionAO;
import net.somta.juggle.console.domain.version.enums.FlowVersionStatusEnum;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.dto.FlowVersionDTO;
import net.somta.juggle.console.interfaces.param.FlowVersionPageParam;
import net.somta.juggle.console.interfaces.param.FlowVersionStatusParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.*;
import static net.somta.juggle.console.domain.version.enums.FlowVersionErrorEnum.ENABLE_FLOW_NOT_DELETE;
import static net.somta.juggle.console.domain.version.enums.FlowVersionErrorEnum.FLOW_NOT_ENABLE;

/**
 * @author husong
 */
@Tag(name = "流程版本接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow/version/")
public class FlowVersionController {

    private final IFlowVersionService flowVersionService;
    private final IFlowRuntimeService flowRuntimeService;

    public FlowVersionController(IFlowVersionService flowVersionService, IFlowRuntimeService flowRuntimeService) {
        this.flowVersionService = flowVersionService;
        this.flowRuntimeService = flowRuntimeService;
    }

    @Operation(summary = "启用或禁用流程版本")
    @PutMapping("/status")
    public ResponseDataResult<Boolean> updateFlowVersionStatus(@RequestBody FlowVersionStatusParam flowVersionStatusParam){
        FlowVersionAO flowVersionAo = flowVersionService.getFlowVersionInfo(flowVersionStatusParam.getFlowVersionId());
        if(flowVersionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        flowVersionAo.setNegateStatus(FlowVersionStatusEnum.getByCode(flowVersionStatusParam.getFlowVersionStatus()));
        flowVersionService.updateFlowVersionStatus(flowVersionAo);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "删除流程版本")
    @DeleteMapping("/delete/{flowVersionId}")
    public ResponseDataResult<Boolean> deleteFlowVersion(@PathVariable Long flowVersionId){
        FlowVersionAO flowVersionAo = flowVersionService.getFlowVersionInfo(flowVersionId);
        if(flowVersionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        if(FlowVersionStatusEnum.ENABLE == flowVersionAo.getFlowVersionStatusEnum()){
            return ResponseDataResult.setErrorResponseResult(ENABLE_FLOW_NOT_DELETE);
        }
        flowVersionService.deleteFlowVersion(flowVersionId);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询流程版本分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowVersionDTO> getFlowVersionPageList(@RequestBody FlowVersionPageParam flowVersionPageParam){
        PageInfo pageInfo = flowVersionService.getFlowVersionPageList(flowVersionPageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Operation(summary = "查询流程的最新部署版本")
    @GetMapping("/latest/{flowKey}")
    public ResponseDataResult<String> getLatestDeployVersion(@PathVariable String flowKey){
        String currentLatestVersion = flowVersionService.getLatestDeployVersion(flowKey);
        if(StringUtils.isEmpty(currentLatestVersion)){
            return ResponseDataResult.setResponseResult("v1");
        }
        String numberVersion =  currentLatestVersion.substring(1);
        String latestVersion = "v" + (Integer.valueOf(numberVersion) + 1);
        return ResponseDataResult.setResponseResult(latestVersion);
    }


    /**
     * 触发流程
     * @param triggerData 触发流程实体
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
