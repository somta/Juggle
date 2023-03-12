package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "变量接口")
@RestController
@RequestMapping("/v1/variable")
public class VariableController {

    @Autowired
    private IVariableService variableService;

    /**
     * 新增变量
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "新增变量")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addVariable(@RequestBody VariableParam variableParam){
        variableService.addVariable();
        return null;
    }

}
