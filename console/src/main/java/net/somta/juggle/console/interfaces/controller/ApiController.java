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
package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.application.service.IApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 * @since 1.0.0
 */
@Tag(name = "API接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/api")
public class ApiController {

    private final IApiService apiService;

    public ApiController(IApiService apiService) {
        this.apiService = apiService;
    }

    @Operation(summary = "新增接口")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addApi(@RequestBody ApiAddParam apiAddParam){
        apiService.addApi(apiAddParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "根据ID删除接口")
    @DeleteMapping("/delete/{apiId}")
    public ResponseDataResult<Boolean> deleteApi(@PathVariable Long apiId){
        apiService.deleteApi(apiId);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "修改接口")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateApi(@RequestBody ApiUpdateParam apiUpdateParam){
        apiService.updateApi(apiUpdateParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询接口详情")
    @GetMapping("/info/{apiId}")
    public ResponseDataResult<ApiInfoDTO> getApi(@PathVariable Long apiId){
        ApiInfoDTO apiInfoDTO = apiService.getApiInfo(apiId);
        return ResponseDataResult.setResponseResult(apiInfoDTO);
    }

    /**
     * 获取API列表
     * @return Boolean
     */
    @Operation(summary = "根据领域ID查询接口列表")
    @PostMapping("/getApiListByDomainId/{domainId}")
    public ResponseDataResult<List<ApiDTO>> getApiListByDomainId(@PathVariable Long domainId){
        List<ApiDTO> apiList = apiService.getApiListByDomainId(domainId);
        return ResponseDataResult.setResponseResult(apiList);
    }

    @Operation(summary = "查询接口分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<ApiDTO>> getApiPageList(@RequestBody ApiQueryParam apiQueryParam){
        PageInfo pageInfo = apiService.getApiPageList(apiQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
