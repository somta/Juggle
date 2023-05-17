package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.model.FlowDefinitionInfo;
import net.somta.juggle.console.model.VariableInfo;
import net.somta.juggle.console.model.param.FlowDefinitionPageParam;
import net.somta.juggle.console.model.param.FlowDefinitionParam;
import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IFlowDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.enums.FlowDefinitionErrorEnum.FLOW_DEFINITION_NOT_EXIST;
import static net.somta.juggle.console.enums.FlowDefinitionErrorEnum.FLOW_PARAM_ERROR;

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
     * @param flowDefinitionParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "创建流程")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addFlowDefinition(@RequestBody FlowDefinitionParam flowDefinitionParam){
        if(flowDefinitionParam == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_PARAM_ERROR);
        }
        Boolean result = flowDefinitionService.addFlowDefinition(flowDefinitionParam);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 删除变量
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "删除流程")
    @DeleteMapping("/delete/{flowDefinitionId}")
    public ResponseDataResult<Boolean> deleteFlowDefinition(@PathVariable Long flowDefinitionId){
        Boolean result = flowDefinitionService.deleteFlowDefinition(flowDefinitionId);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 修改流程
     * @param flowDefinitionParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "修改流程")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateFlowDefinition(@RequestBody FlowDefinitionParam flowDefinitionParam){
        if(flowDefinitionParam == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_PARAM_ERROR);
        }
        Boolean result = flowDefinitionService.updateFlowDefinition(flowDefinitionParam);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 获取流程列表
     * @param flowDefinitionPageParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "获取流程列表")
    @PostMapping("/list")
    public ResponsePaginationDataResult<FlowDefinitionInfo> getFlowDefinitionList(@RequestBody FlowDefinitionPageParam flowDefinitionPageParam){
        List<FlowDefinitionInfo> list = flowDefinitionService.getFlowDefinitionList(flowDefinitionPageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(10L,list);
    }

    /**
     * 部署上线流程
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "部署流程")
    @GetMapping("/deploy/{flowDefinitionId}/{version}")
    public ResponseDataResult<Boolean> deployFlowDefinition(@PathVariable Long flowDefinitionId, @PathVariable String version){
        FlowDefinitionInfo flowDefinitionInfo = flowDefinitionService.getFlowDefinitionById(flowDefinitionId);
        if(flowDefinitionInfo == null){
            return ResponseDataResult.setErrorResponseResult(FLOW_DEFINITION_NOT_EXIST);
        }
        Boolean result = flowDefinitionService.deployFlowDefinition(flowDefinitionInfo,version);
        return ResponseDataResult.setResponseResult(result);
    }
}
