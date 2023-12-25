package net.somta.juggle.console.interfaces.controller.flow;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.flow.IFlowTemplateService;
import net.somta.juggle.console.interfaces.dto.flow.FlowDefinitionInfoDTO;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import net.somta.juggle.console.interfaces.param.flow.definition.FlowDefinitionPageParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "流程定义接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/flow/template")
public class FlowTemplateController {

    private final IFlowTemplateService flowTemplateService;

    public FlowTemplateController(IFlowTemplateService flowTemplateService) {
        this.flowTemplateService = flowTemplateService;
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

}
