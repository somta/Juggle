package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.ApiAddParam;
import net.somta.juggle.console.model.param.ApiQueryParam;
import net.somta.juggle.console.model.param.DomainQueryParam;
import net.somta.juggle.console.model.param.DomainUpdateParam;
import net.somta.juggle.console.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "API接口（已完成）")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/api")
public class ApiController {

    @Autowired
    private IApiService apiService;


    @Operation(summary = "新增接口")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addApi(@RequestBody ApiAddParam apiAddParam){
        Api api = new Api();
        api.setDomainId(apiAddParam.getDomainId());
        api.setApiUrl(apiAddParam.getApiUrl());
        api.setApiName(apiAddParam.getApiName());
        api.setApiDesc(apiAddParam.getApiDesc());
        api.setApiRequestType(apiAddParam.getApiRequestType());
        api.setApiRequestContentType(apiAddParam.getApiRequestContentType());
        apiService.add(api);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "根据ID删除接口")
    @DeleteMapping("/delete/{apiId}")
    public ResponseDataResult<Boolean> addApi(@PathVariable Long apiId){
        return apiService.deleteById(apiId);
    }

    @Operation(summary = "修改接口")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> addDomain(@RequestBody Api api){
        return apiService.update(api);
    }

    /**
     * 获取API列表
     * @return Boolean
     */
    @Operation(summary = "根据领域ID查询接口列表")
    @PostMapping("/getApiListByDomainId/{domainId}")
    public ResponseDataResult<List<Api>> getApiListByDomainId(@PathVariable Long domainId){
        List<Api> apiList = apiService.getApiListByDomainId(domainId);
        return ResponseDataResult.setResponseResult(apiList);
    }

    @Operation(summary = "查询接口分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<DomainDTO>> getApiPageList(@RequestBody ApiQueryParam apiQueryParam){
        return apiService.queryByPageList(apiQueryParam.getPageNum(),apiQueryParam.getPageSize(), apiQueryParam);
    }

}
