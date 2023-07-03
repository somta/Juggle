package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.application.service.IParameterService;
import net.somta.juggle.console.domain.parameter.enums.ParameterSourceTypeEnum;
import net.somta.juggle.console.domain.parameter.enums.ParameterTypeEnum;
import net.somta.juggle.console.domain.parameter.vo.ParameterVO;
import net.somta.juggle.console.infrastructure.model.Parameter;
import net.somta.juggle.console.interfaces.dto.ApiInfoDTO;
import net.somta.juggle.console.interfaces.param.ApiAddParam;
import net.somta.juggle.console.interfaces.param.ApiQueryParam;
import net.somta.juggle.console.interfaces.param.ApiUpdateParam;
import net.somta.juggle.console.infrastructure.model.Api;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.application.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "API接口（已完成）")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/api")
public class ApiController {

    @Autowired
    private IApiService apiService;
    @Autowired
    private IParameterService parameterService;


    @Operation(summary = "新增接口")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addApi(@RequestBody ApiAddParam apiAddParam){
        return apiService.addApi(apiAddParam);
    }

    @Operation(summary = "根据ID删除接口")
    @DeleteMapping("/delete/{apiId}")
    public ResponseDataResult<Boolean> addApi(@PathVariable Long apiId){
        return apiService.deleteById(apiId);
    }

    @Operation(summary = "修改接口")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateApi(@RequestBody ApiUpdateParam apiUpdateParam){
        return apiService.updateApi(apiUpdateParam);
    }

    @Operation(summary = "查询接口详情")
    @PutMapping("/info")
    public ResponseDataResult<ApiInfoDTO> queryApi(Long id){
        ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
        Api api = apiService.queryById(id);
        apiInfoDTO.setId(api.getId());
        apiInfoDTO.setApiName(api.getApiName());
        apiInfoDTO.setApiDesc(api.getApiDesc());
        List<Parameter> parameters = parameterService.getParameterListByVO(new ParameterVO(ParameterSourceTypeEnum.API.getCode(),id));
        List<Parameter> inputParams = parameters.stream()
                .filter(parameter -> ParameterTypeEnum.INPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        List<Parameter> outputParams = parameters.stream()
                .filter(parameter -> ParameterTypeEnum.OUTPUT_PARAM.getCode() == parameter.getParamType())
                .collect(Collectors.toList());
        apiInfoDTO.setApiInputParams(inputParams);
        apiInfoDTO.setApiOutputParams(outputParams);
        return ResponseDataResult.setResponseResult(apiInfoDTO);
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
    public ResponsePaginationDataResult<List<ApiDTO>> getApiPageList(@RequestBody ApiQueryParam apiQueryParam){
        return apiService.queryApiPageList(apiQueryParam);
    }

}
