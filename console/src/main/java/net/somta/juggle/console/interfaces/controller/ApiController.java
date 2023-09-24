package net.somta.juggle.console.interfaces.controller;

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

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

/**
 * @author husong
 */
@Tag(name = "API接口（已完成）")
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
        return apiService.addApi(apiAddParam);
    }

    @Operation(summary = "根据ID删除接口")
    @DeleteMapping("/delete/{apiId}")
    public ResponseDataResult<Boolean> deleteApi(@PathVariable Long apiId){
        return apiService.deleteApi(apiId);
    }

    @Operation(summary = "修改接口")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateApi(@RequestBody ApiUpdateParam apiUpdateParam){
        return apiService.updateApi(apiUpdateParam);
    }

    @Operation(summary = "查询接口详情")
    @GetMapping("/info/{apiId}")
    public ResponseDataResult<ApiInfoDTO> queryApi(@PathVariable Long apiId){
        ApiInfoDTO apiInfoDTO = apiService.queryApiInfo(apiId);
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
        return apiService.queryApiPageList(apiQueryParam);
    }

}
