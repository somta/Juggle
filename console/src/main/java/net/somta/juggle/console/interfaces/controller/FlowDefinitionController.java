package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.param.FlowDefinitionPageParam;
import net.somta.juggle.console.interfaces.param.FlowDefinitionAddParam;
import net.somta.juggle.console.application.service.IFlowDefinitionService;
import net.somta.juggle.console.interfaces.param.FlowDefinitionUpdateParam;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.definition.enums.FlowDefinitionErrorEnum.FLOW_DEFINITION_NOT_EXIST;
import static net.somta.juggle.console.domain.definition.enums.FlowDefinitionErrorEnum.FLOW_PARAM_ERROR;
import static net.somta.juggle.console.domain.flow.enums.FlowErrorEnum.*;

/**
 * 流程定义Controller
 * @author husong
 * @date 2022/12/17
 **/
@Tag(name = "流程定义接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow/definition")
public class FlowDefinitionController {

    @Autowired
    private IFlowDefinitionService flowDefinitionService;

    /**
     * 创建流程
     * @param flowDefinitionAddParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "创建流程定义")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addFlowDefinition(@RequestBody FlowDefinitionAddParam flowDefinitionAddParam){
        if(flowDefinitionAddParam == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_PARAM_ERROR);
        }
        Boolean result = flowDefinitionService.addFlowDefinition(flowDefinitionAddParam);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 删除变量
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "删除流程定义")
    @DeleteMapping("/delete/{flowDefinitionId}")
    public ResponseDataResult<Boolean> deleteFlowDefinition(@PathVariable Long flowDefinitionId){
        Boolean result = flowDefinitionService.deleteFlowDefinition(flowDefinitionId);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 修改流程
     * @param flowDefinitionUpdateParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "修改流程定义")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateFlowDefinition(@RequestBody FlowDefinitionUpdateParam flowDefinitionUpdateParam){
        if(flowDefinitionUpdateParam == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_PARAM_ERROR);
        }
        Boolean result = flowDefinitionService.updateFlowDefinition(flowDefinitionUpdateParam);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 获取流程列表
     * @param flowDefinitionPageParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "获取流程定义分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowDefinitionInfoPO> getFlowDefinitionList(@RequestBody FlowDefinitionPageParam flowDefinitionPageParam){
        return flowDefinitionService.queryByPageList(flowDefinitionPageParam.getPageNum(),flowDefinitionPageParam.getPageSize(), flowDefinitionPageParam);
    }

    /**
     * 调试流程
     * @param triggerData 触发流程实体
     * @return Boolean
     */
    @Operation(summary = "调试流程")
    @PostMapping("/debug/{flowKey}")
    public ResponseDataResult<FlowResult> debugFlow(@PathVariable String flowKey, @RequestBody TriggerDataParam triggerData){
        if(StringUtils.isEmpty(flowKey)){
            return ResponseDataResult.setErrorResponseResult(FLOW_KEY_IS_EMPTY);
        }
        FlowDefinitionInfoPO flowDefinitionInfoPO = flowDefinitionService.getFlowDefinitionByKey(flowKey);
        if(flowDefinitionInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_NOT_EXIST);
        }
        FlowResult rst = flowDefinitionService.debugFlow(flowDefinitionInfoPO,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }

    /**
     * 部署上线流程
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "部署流程")
    @GetMapping("/deploy/{flowDefinitionId}")
    public ResponseDataResult<Boolean> deployFlowDefinition(@PathVariable Long flowDefinitionId){
        FlowDefinitionInfoPO flowDefinitionInfoPO = flowDefinitionService.getFlowDefinitionById(flowDefinitionId);
        if(flowDefinitionInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_DEFINITION_NOT_EXIST);
        }
        Boolean result = flowDefinitionService.deployFlowDefinition(flowDefinitionInfoPO);
        return ResponseDataResult.setResponseResult(result);
    }
}
