package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.Api;
import net.somta.juggle.console.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "领域接口")
@RestController
@RequestMapping("/v1/domain")
public class DomainController {

    @Autowired
    private IApiService apiService;

    /**
     * 获取领域列表
     * @return Boolean
     */
    @Operation(summary = "获取领域列表")
    @PostMapping("/list")
    public ResponseDataResult<List<Api>> getApiList(){
        List<Api> apiList = apiService.getApiList();
        return ResponseDataResult.setResponseResult(apiList);
    }

}
