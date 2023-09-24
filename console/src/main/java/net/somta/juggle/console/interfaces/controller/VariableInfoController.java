package net.somta.juggle.console.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.console.domain.definition.enums.FlowDefinitionErrorEnum;
import net.somta.juggle.console.domain.variable.enums.VariableErrorEnum;
import net.somta.juggle.console.infrastructure.po.VariableInfoPO;
import net.somta.juggle.console.interfaces.param.VariableAddParam;
import net.somta.juggle.console.application.service.IVariableInfoService;
import net.somta.juggle.core.model.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.somta.juggle.console.contants.ApplicationContants.JUGGLE_SERVER_VERSION;

@Tag(name = "变量接口")
@RestController
@RequestMapping(JUGGLE_SERVER_VERSION + "/variable")
public class VariableInfoController {

    @Autowired
    private IVariableInfoService variableService;

    /**
     * 新增变量
     * @param variableAddParam 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "新增变量")
    @PostMapping("/add")
    public ResponseDataResult<Boolean> addVariable(@RequestBody VariableAddParam variableAddParam){
        if(variableAddParam == null){
            return ResponseDataResult.setErrorResponseResult(VariableErrorEnum.VARIABLE_PARAM_IS_NULL_ERROR);
        }
        VariableInfoPO variableInfoPO = new VariableInfoPO();
        variableInfoPO.setEnvKey(variableAddParam.getEnvKey());
        variableInfoPO.setEnvName(variableAddParam.getEnvName());
        variableInfoPO.setDataType(JsonSerializeHelper.serialize(variableAddParam.getDataType()));
        variableInfoPO.setFlowDefinitionId(variableAddParam.getFlowDefinitionId());
        Boolean result = variableService.addVariable(variableInfoPO);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 删除变量
     * @param flowDefinitionId 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "删除变量")
    @DeleteMapping("/delete/{flowDefinitionId}/{variableId}")
    public ResponseDataResult<Boolean> deleteVariable(@PathVariable Long flowDefinitionId, @PathVariable Long variableId){
        if(flowDefinitionId == null){
            return ResponseDataResult.setErrorResponseResult(FlowDefinitionErrorEnum.FLOW_DEFINITION_ID_IS_NULL_ERROR);
        }
        if(variableId == null){
            return ResponseDataResult.setErrorResponseResult(VariableErrorEnum.VARIABLE_ID_IS_NULL_ERROR);
        }
        Boolean result = variableService.deleteVariable(flowDefinitionId, variableId);
        return ResponseDataResult.setResponseResult(result);
    }

    /**
     * 修改
     * @param variableInfoPO 变量实体参数
     * @return Boolean
     */
    @Operation(summary = "修改变量")
    @PutMapping("/update")
    public ResponseDataResult<Boolean> updateVariable(@RequestBody VariableInfoPO variableInfoPO){
        if(variableInfoPO == null){
            return ResponseDataResult.setErrorResponseResult(VariableErrorEnum.VARIABLE_PARAM_IS_NULL_ERROR);
        }
        Boolean result = variableService.updateVariable(variableInfoPO);
        return ResponseDataResult.setResponseResult(result);
    }


    /**
     * 修改
     * @return Boolean
     */
    @Operation(summary = "获取流程内的变量列表")
    @GetMapping("/list/{flowDefinitionId}")
    public ResponseDataResult<List<Variable>> getVariableInfoList(Long flowDefinitionId){
        if(flowDefinitionId == null){
            return ResponseDataResult.setErrorResponseResult(FlowDefinitionErrorEnum.FLOW_DEFINITION_ID_IS_NULL_ERROR);
        }
        List<Variable> list = variableService.getFlowVariableList(flowDefinitionId);
        return ResponseDataResult.setResponseResult(list);
    }


}
