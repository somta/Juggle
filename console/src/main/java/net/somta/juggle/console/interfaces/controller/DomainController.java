package net.somta.juggle.console.interfaces.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.core.protocol.ResponsePaginationDataResult;
import net.somta.juggle.console.domain.domain.enums.DomainErrorEnum;
import net.somta.juggle.console.interfaces.dto.ApiDTO;
import net.somta.juggle.console.interfaces.dto.DomainDTO;
import net.somta.juggle.console.interfaces.param.DomainAddParam;
import net.somta.juggle.console.interfaces.param.DomainQueryParam;
import net.somta.juggle.console.interfaces.param.DomainUpdateParam;
import net.somta.juggle.console.application.service.IApiService;
import net.somta.juggle.console.application.service.IDomainService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "领域接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/domain")
public class DomainController {

    private final IDomainService domainService;
    private final IApiService apiService;

    public DomainController(IDomainService domainService, IApiService apiService) {
        this.domainService = domainService;
        this.apiService = apiService;
    }

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
        List<ApiDTO> apiPOS = apiService.getApiListByDomainId(domainId);
        if(CollectionUtils.isNotEmpty(apiPOS)){
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
    @GetMapping("/list")
    public ResponseDataResult<List<DomainDTO>> getAllDomainList(){
        List<DomainDTO> list = domainService.getAllDomainList();
        return ResponseDataResult.setResponseResult(list);
    }

    /**
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域分页列表")
    @PostMapping("/page")
    public ResponsePaginationDataResult<List<DomainDTO>> getDomainPageList(@RequestBody DomainQueryParam domainQueryParam){
        PageInfo pageInfo = domainService.getDomainPageList(domainQueryParam);
        return ResponsePaginationDataResult.setPaginationDataResult(pageInfo.getTotal(),pageInfo.getList());
    }

}
