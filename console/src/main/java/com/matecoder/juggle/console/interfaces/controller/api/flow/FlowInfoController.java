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
package com.matecoder.juggle.console.interfaces.controller.api.flow;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.matecoder.core.protocol.ResponseDataResult;
import com.matecoder.core.protocol.ResponsePaginationDataResult;
import com.matecoder.juggle.console.interfaces.dto.flow.FlowInfoDTO;
import com.matecoder.juggle.console.interfaces.param.flow.FlowInfoPageParam;
import com.matecoder.juggle.console.application.service.flow.IFlowInfoService;
import org.springframework.web.bind.annotation.*;

import static com.matecoder.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;

/**
 * @author husong
 * @since 1.0.0
 */
@Tag(name = "流程信息接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/flow")
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

}
