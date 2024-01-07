package net.somta.juggle.console.interfaces.controller.flow;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.flow.IFlowTemplateService;
import net.somta.juggle.console.interfaces.dto.flow.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionPageParam;
import org.springframework.web.bind.annotation.*;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "流程模板接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow/template")
public class FlowTemplateController {

    private final IFlowTemplateService flowTemplateService;

    public FlowTemplateController(IFlowTemplateService flowTemplateService) {
        this.flowTemplateService = flowTemplateService;
    }

    @Operation(summary = "删除流程模板")
    @DeleteMapping("/delete/{templateId}")
    public ResponseDataResult<Boolean> deleteFlowTemplate(@PathVariable Long templateId){
        Boolean result = flowTemplateService.deleteFlowTemplate(templateId);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 获取流程模板分页列表
     * @param flowTemplatePageParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "获取流程模板分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<FlowTemplateInfoDTO> getFlowDefinitionPageList(@RequestBody FlowTemplatePageParam flowTemplatePageParam){
        PageInfo pageInfo = flowTemplateService.getFlowTemplatePageList(flowTemplatePageParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }


    @Operation(summary = "使用流程模板")
    @DeleteMapping("/apply/{templateId}")
    public ResponseDataResult<Long> applyFlowTemplate(@PathVariable Long templateId){
        Long flowDefinitionId = flowTemplateService.applyFlowTemplate(templateId);
        return ResponseDataResult.setResponseResult(flowDefinitionId);
    }


}
