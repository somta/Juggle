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
package net.somta.juggle.console.interfaces.controller.api.suite;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.exception.BizException;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.suite.ISuiteService;
import net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteTypeEnum;
import net.somta.juggle.console.domain.suite.suiteinfo.vo.SuiteVO;
import net.somta.juggle.console.interfaces.dto.suite.ApiInfoDTO;
import net.somta.juggle.console.interfaces.dto.suite.SuiteMarketInfoDTO;
import net.somta.juggle.console.interfaces.param.suite.ApiAddParam;
import net.somta.juggle.console.interfaces.param.suite.ApiDebugParam;
import net.somta.juggle.console.interfaces.param.suite.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.suite.ApiUpdateParam;
import net.somta.juggle.console.interfaces.dto.suite.ApiDTO;
import net.somta.juggle.console.application.service.suite.IApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_API_PREFIX;
import static net.somta.juggle.common.constants.ApplicationConstants.JUGGLE_SERVER_VERSION;
import static net.somta.juggle.console.domain.suite.api.enums.ApiErrorEnum.API_NOT_EDIT_ERROR;
import static net.somta.juggle.console.domain.suite.suiteinfo.enums.SuiteErrorEnum.SUITE_IS_EXIST_ERROR;

/**
 * @author husong
 * @since 1.0.0
 */
@Tag(name = "API接口")
@RestController
@RequestMapping(JUGGLE_API_PREFIX + "/api")
public class ApiController {

    private final IApiService apiService;
    private final ISuiteService suiteService;

    public ApiController(IApiService apiService, ISuiteService suiteService) {
        this.apiService = apiService;
        this.suiteService = suiteService;
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
        SuiteVO suiteVo = suiteService.getSuiteInfo(apiUpdateParam.getSuiteId());
        if(suiteVo!=null && SuiteTypeEnum.OFFICIAL_SUITE.getCode() == suiteVo.getSuiteFlag()){
            throw new BizException(API_NOT_EDIT_ERROR);
        }
        apiService.updateApi(apiUpdateParam);
        return ResponseDataResult.setResponseResult();
    }

    @Operation(summary = "查询接口详情")
    @GetMapping("/info/{apiId}")
    public ResponseDataResult<ApiInfoDTO> getApi(@PathVariable Long apiId){
        ApiInfoDTO apiInfoDTO = apiService.getApiInfo(apiId);
        return ResponseDataResult.setResponseResult(apiInfoDTO);
    }
    @Operation(summary = "根据编码查询接口详情")
    @GetMapping("/info/code/{apiCode}")
    public ResponseDataResult<ApiInfoDTO> getApiByCode(@PathVariable String apiCode){
        ApiInfoDTO apiInfoDTO = apiService.getApiInfoByCode(apiCode);
        return ResponseDataResult.setResponseResult(apiInfoDTO);
    }


    @Operation(summary = "根据套件ID查询接口列表")
    @PostMapping("/getApiListBySuiteId/{suiteId}")
    public ResponseDataResult<List<ApiDTO>> getApiListBySuiteId(@PathVariable Long suiteId){
        List<ApiDTO> apiList = apiService.getApiListBySuiteId(suiteId);
        return ResponseDataResult.setResponseResult(apiList);
    }

    @Operation(summary = "根据套件编码查询接口列表")
    @PostMapping("/getApiListBySuiteCode/{suiteCode}")
    public ResponseDataResult<List<ApiDTO>> getApiListBySuiteId(@PathVariable String suiteCode){
        List<ApiDTO> apiList = apiService.getApiListBySuiteCode(suiteCode);
        return ResponseDataResult.setResponseResult(apiList);
    }

    @Operation(summary = "查询接口分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<ApiDTO> getApiPageList(@RequestBody ApiQueryParam apiQueryParam){
        PageInfo pageInfo = apiService.getApiPageList(apiQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Operation(summary = "调试接口")
    @PostMapping("/debug/{apiId}")
    public ResponseDataResult<Map<String,Object>> debugApi(@PathVariable Long apiId,@RequestBody ApiDebugParam apiDebugParam){
        Map<String,Object> result = apiService.debugApi(apiId,apiDebugParam);
        return ResponseDataResult.setResponseResult(result);
    }

}
