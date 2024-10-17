---
description: 普通Java项目快速集成Juggle，完成Juggle能力的融入，增强业务系统能力。
keywords: [Java集成Juggle, 微服务接口, 接口编排]
---

# 普通Java项目集成Juggle

为了方便一个普通的Java项目也能快速接入Juggle的流程，我们也提供了对应的client，通过该client就能快速接入和触发流程，具体接入步骤如下：

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

### 3.通过juggleClient调用流程接口

```java
public static void main(String[] args) throws IOException {
        //1.实例化Juggle客户端
        JuggleConfig juggleConfig = new JuggleConfig();
        juggleConfig.setServerAddr("https://demo.juggle.plus");
        juggleConfig.setAccessToken("eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcyOTAwODYzOTc5MH0=");
        JuggleClient juggleClient = JuggleFactory.getClientInstance(juggleConfig);

        //2.组装流程参数
        FlowTriggerDataParam flowTriggerDataParam = new FlowTriggerDataParam();
        Map<String,Object> data = new HashMap<>();
        data.put("userName","juggle");
        data.put("password","123456");
        data.put("deposit","1000");
        flowTriggerDataParam.setFlowData(data);

        //3.触发流程
        ResponseDataResult<FlowResultModel> result =    juggleClient.triggerFlow("v1","sync_example",flowTriggerDataParam);
        FlowResultModel flowResultModel = result.getResult();
        System.out.println(flowResultModel.getStatus());
        System.out.println(flowResultModel.getData());

 }
```



:::tip

上诉代码中Juggle服务器的访问地址(serverAddr)和访问凭证(accessToken)，这里并没有采用从配置文件读取的方式，因为在普通的java项目中，并没有统一的配置读取方案，因此交于用户根据实际情况自行实现

:::

### 4.juggleClient提供方法介绍

#### a.触发流程方法  - triggerFlow

**入参**

| 名称        | 类型   | 是否必填 | 描述                                             |
| ----------- | ------ | -------- | ------------------------------------------------ |
| flowVersion | String | 必填     | 流程版本                                         |
| flowKey     | String | 必填     | 流程Key                                          |
| triggerData | Object | 非必填   | 流程定义中的入参数据，如果流程没有入参，可以不填 |
| - flowData  | Map    | 非必填   | 触发流程需要的参数数据                           |

**出参**

| 名称             | 类型    | 描述                                       |
| ---------------- | ------- | ------------------------------------------ |
| success          | Boolean | 是否成功                                   |
| errorCode        | Long    | 错误码                                     |
| errorMsg         | String  | 错误信息                                   |
| result           | Object  | 流程定义中设置的出参结果                   |
| - flowInstanceId | String  | 流程触发后的实例ID                         |
| - status         | String  | 流程的执行状态                             |
| - data           | Map     | 流程返回的实际数据，即流程定义中定义的出参 |



#### b.获取异步流程方法 - getAsyncFlowResult

**入参**

| 名称           | 类型   | 是否必填 | 描述           |
| -------------- | ------ | -------- | -------------- |
| flowInstanceId | String | 必填     | 异步流程实例ID |

**出参**

| 名称      | 类型    | 描述                     |
| --------- | ------- | ------------------------ |
| success   | Boolean | 是否成功                 |
| errorCode | Long    | 错误码                   |
| errorMsg  | String  | 错误信息                 |
| result    | Map     | 流程定义中设置的出参结果 |