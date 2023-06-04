package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.enums.error.DomainErrorEnum;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.DomainAddParam;
import net.somta.juggle.console.model.param.DomainQueryParam;
import net.somta.juggle.console.model.param.DomainUpdateParam;
import net.somta.juggle.console.service.IApiService;
import net.somta.juggle.console.service.IDomainService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "领域接口（已完成）")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/domain")
public class DomainController {

    @Autowired
    private IDomainService domainService;
    @Autowired
    private IApiService apiService;

    /**
     * 新增领域
     * @return Boolean
     */
    @Operation(summary = "新增领域")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addDomain(@RequestBody DomainAddParam domainAddParam){
        domainService.addDomain(domainAddParam);
        return ResponseDataResult.setResponseResult(true);
    }

    /**
     * 修改领域
     * @return Boolean
     */
    @Operation(summary = "修改领域")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> addDomain(@RequestBody DomainUpdateParam domainUpdateParam){
        domainService.updateDomain(domainUpdateParam);
        return ResponseDataResult.setResponseResult(true);
    }

    @Operation(summary = "根据ID删除领域")
    @DeleteMapping("/delete/{domainId}")
    public ResponseDataResult<Boolean> deleteDomain(@PathVariable Long domainId){
        List<Api> apis = apiService.getApiListByDomainId(domainId);
        if(CollectionUtils.isNotEmpty(apis)){
            return ResponseDataResult.setErrorResponseResult(DomainErrorEnum.DOMAIN_EXIST_API_ERROR);
        }
        domainService.deleteDomain(domainId);
        return ResponseDataResult.setResponseResult(true);
    }

    /**
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域列表")
    @PostMapping("/list")
    public ResponseDataResult<List<DomainDTO>> getDomainList(){
        List<DomainDTO> list = domainService.getDomainList();
        return ResponseDataResult.setResponseResult(list);
    }

    /**
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<DomainDTO>> getDomainPageList(@RequestBody DomainQueryParam domainQueryParam){
        return domainService.queryByPageList(domainQueryParam.getPageNum(),domainQueryParam.getPageSize(), domainQueryParam);
    }

}
