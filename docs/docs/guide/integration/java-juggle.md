---
description: 普通Java项目快速集成Juggle，完成Juggle能力的融入，增强业务系统能力。
keywords: [Java集成Juggle, 微服务接口, 接口编排]
---

# 普通Java项目集成Juggle

SpringBoot作为Java开发最常见的框架，我们提供了对应的Starter的，通过Starter我们能快速接入Juggle，具体接入步骤如下：

### 1.添加依赖

```xml
<dependency>
    <groupId>net.somta</groupId>
    <artifactId>juggle-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2.登录Juggle，申请一个令牌

![](/juggle/images/guide/user/gen_token.png)

### 3.在项目的的配置文件中添加如下配置

```properties
#Juggle服务部署的地址
juggle.server-addr=http://127.0.0.1:9127
#Juggle后台申请的令牌
juggle.access-token=eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcxMjE2MzgyNzE2OH0=
```

### 4.通过IJuggleTemplate调用流程接口

```java
@Tag(name = "业务服务集成Juggle接口")
@RestController
@RequestMapping("/flow")
public class FlowIntegrationController {

    @Autowired
    private IJuggleTemplate juggleTemplate;

    @Operation(summary = "集成触发流程")
    @PostMapping("/triggerFlow")
    public BaseResponse<FlowResultModel> triggerFlow(@RequestBody TriggerFlowParam triggerFlowParam){
        try {
            ResponseDataResult<FlowResultModel> flowResult =              juggleTemplate.triggerFlow(triggerFlowParam.getFlowVersion(),
                    triggerFlowParam.getFlowKey(),triggerFlowParam.getTriggerData());
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
```