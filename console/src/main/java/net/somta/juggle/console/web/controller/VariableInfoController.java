package net.somta.juggle.console.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.model.param.VariableParam;
import net.somta.juggle.console.service.IVariableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "变量接口")
@RestController
@RequestMapping("/v1/variable")
public class VariableInfoController {

    @Autowired
    private IVariableInfoService variableService;

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

    /**
     * 新增变量
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "删除变量")
    @DeleteMapping("/delete/{flowDefinitionId}/{key}")
    public ResponseDataResult<Boolean> deleteVariable(@RequestBody VariableParam variableParam){
        variableService.addVariable();
        return null;
    }

    /**
     * 修改
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "修改变量")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateVariable(@RequestBody VariableParam variableParam){
        variableService.addVariable();
        return null;
    }


    /**
     * 修改
     * @param variableParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "获取流程内的变量列表")
    @GetMapping("/list/{flowDefinitionId}")
    public ResponseDataResult<Boolean> getVariableInfoList(@RequestBody VariableParam variableParam){
        variableService.addVariable();
        return null;
    }


}
