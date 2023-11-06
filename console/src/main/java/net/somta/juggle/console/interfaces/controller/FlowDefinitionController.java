package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.assembler.IFlowDefinitionAssembler;
import net.somta.juggle.console.domain.definition.FlowDefinitionAO;
import net.somta.juggle.console.domain.definition.enums.FlowDefinitionErrorEnum;
import net.somta.juggle.console.infrastructure.po.FlowDefinitionInfoPO;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.dto.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.param.definition.*;
import net.somta.juggle.console.application.service.IFlowDefinitionService;
import net.somta.juggle.console.interfaces.param.TriggerDataParam;
import net.somta.juggle.core.model.FlowResult;
import org.apache.commons.lang3.StringUtils;
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

    private final IFlowDefinitionService flowDefinitionService;

    public FlowDefinitionController(IFlowDefinitionService flowDefinitionService) {
        this.flowDefinitionService = flowDefinitionService;
    }

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

    @Operation(summary = "保存流程内容")
    @PutMapping("/save")
    public ResponseDataResult<Boolean> saveFlowDefinitionContent(@RequestBody FlowDefinitionContentParam flowDefinitionContentParam){
        if(flowDefinitionContentParam == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_PARAM_ERROR);
        }
        Boolean result = flowDefinitionService.saveFlowDefinitionContent(flowDefinitionContentParam);
        return ResponseDataResult.setResponseResult(result);
    }

    @Operation(summary = "查询流程定义详情")
    @GetMapping("/info/{flowDefinitionId}")
    public ResponseDataResult<FlowDefinitionInfoDTO> getFlowDefinitionInfo(@PathVariable Long flowDefinitionId){
        FlowDefinitionAO flowDefinitionAo = flowDefinitionService.getFlowDefinitionInfo(flowDefinitionId);
        FlowDefinitionInfoDTO flowDefinitionInfoDto = IFlowDefinitionAssembler.IMPL.aoToDto(flowDefinitionAo);
        return ResponseDataResult.setResponseResult(flowDefinitionInfoDto);
    }

    /**
     * 获取流程列表
     * @param flowDefinitionPageParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "获取流程定义分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowDefinitionInfoPO> getFlowDefinitionPageList(@RequestBody FlowDefinitionPageParam flowDefinitionPageParam){
        PageInfo pageInfo = flowDefinitionService.getFlowDefinitionPageList(flowDefinitionPageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
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
        FlowDefinitionAO flowDefinitionAo = flowDefinitionService.getFlowDefinitionByKey(flowKey);
        if(flowDefinitionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_DEFINITION_NOT_EXIST);
        }
        if(StringUtils.isEmpty(flowDefinitionAo.getFlowContent())){
            return ResponseDataResult.setErrorResponseResult(FlowDefinitionErrorEnum.FLOW_DEFINITION_CONTENT_IS_NULL_ERROR);
        }
        FlowResult rst = flowDefinitionService.debugFlow(flowDefinitionAo,triggerData);
        return ResponseDataResult.setResponseResult(rst);
    }

    /**
     * 部署上线流程
     * @param flowDefinitionDeployParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "部署流程")
    @PostMapping("/deploy")
    public ResponseDataResult<Boolean> deployFlowDefinition(@RequestBody FlowDefinitionDeployParam flowDefinitionDeployParam){
        FlowDefinitionAO flowDefinitionAo = flowDefinitionService.getFlowDefinitionInfo(flowDefinitionDeployParam.getFlowDefinitionId());
        if(flowDefinitionAo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_DEFINITION_NOT_EXIST);
        }
        Boolean result = flowDefinitionService.deployFlowDefinition(flowDefinitionDeployParam.getFlowDeployVersion(),flowDefinitionAo);
        return ResponseDataResult.setResponseResult(result);
    }
}
