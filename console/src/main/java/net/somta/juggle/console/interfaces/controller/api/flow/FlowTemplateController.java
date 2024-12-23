/*
Copyright (C) 2022-2024 husong

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, visit <https://www.gnu.org/licenses/gpl-3.0.html>.
*/
package net.somta.juggle.console.interfaces.controller.api.flow;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.flow.IFlowTemplateService;
import net.somta.juggle.console.interfaces.dto.flow.FlowTemplateInfoDTO;
import net.somta.juggle.console.interfaces.param.flow.FlowTemplatePageParam;
import org.springframework.web.bind.annotation.*;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.0.0
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
