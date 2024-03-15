package net.somta.juggle.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.example.dto.BaseResponse;
import net.somta.juggle.example.param.TriggerFlowParam;
import net.somta.juggle.starter.impl.IJuggleTemplate;
import net.somta.juggle.starter.model.FlowResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author husong
 */
@Tag(name = "外部服务集成Juggle接口")
@RestController
@RequestMapping("/flow")
public class FlowIntegrationController {

    @Autowired
    private IJuggleTemplate juggleTemplate;

    @Operation(summary = "集成触发流程")
    @PostMapping("/triggerFlow")
    public BaseResponse<FlowResultModel> triggerFlow(TriggerFlowParam triggerFlowParam){
        try {
            ResponseDataResult<FlowResultModel> flowResult = juggleTemplate.triggerFlow(triggerFlowParam.getFlowVersion(),
                    triggerFlowParam.getFlowKey(),
                    triggerFlowParam.getTriggerData());
            if(flowResult.isSuccess()){
                return BaseResponse.setSuccessResponse(flowResult.getResult());
            }else {
                return BaseResponse.setErrorResponse(flowResult.getErrorMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.setErrorResponse("调用流程异常了");
        }
    }

    @Operation(summary = "集成获取异步流程结果")
    @PostMapping("/getAsyncFlowResult")
    public BaseResponse<Map<String,Object>> getAsyncFlowResult(String flowInstanceId){
        try {
            ResponseDataResult<Map<String,Object>> asyncFlowResult = juggleTemplate.getAsyncFlowResult(flowInstanceId);
            if(asyncFlowResult.isSuccess()){
                return BaseResponse.setSuccessResponse(asyncFlowResult.getResult());
            }else {
                return BaseResponse.setErrorResponse(asyncFlowResult.getErrorMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResponse.setErrorResponse("调用异步流程流程异常了");
        }
    }

}
