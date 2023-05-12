package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.model.dto.DomainDTO;
import net.somta.juggle.console.model.param.DomainAddParam;
import net.somta.juggle.console.service.IApiService;
import net.somta.juggle.console.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "领域接口")
@RestController
@RequestMapping("/v1/domain")
public class DomainController {

    @Autowired
    private IDomainService domainService;

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
     * 查询领域列表
     * @return Boolean
     */
    @Operation(summary = "查询领域列表")
    @PostMapping("/getDomainList")
    public ResponseDataResult<List<DomainDTO>> getDomainList(){
        List<DomainDTO> list = domainService.getDomainList();
        return ResponseDataResult.setResponseResult(list);
    }

}
