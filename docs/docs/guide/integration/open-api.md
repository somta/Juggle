---
description: 业务系统如何快速集成Juggle，完成业务系统与流程的对接。
keywords: [Juggle, 业务系统集成Juggle, Juggle Open API]
---

# OpenApi列表
Juggle提供了OpenApi,让业务系统能快速接入Juggle的能力，同时为了简化业务服务集成的过程，还提供了不同语言的sdk，具体接入步骤请参考：[业务系统集成Juggle](https://www.juggle.plus/docs/guide/integration/)

## 一.api规范

#### 1.统一响应格式

OpenApi所有接口响应均为json类型的返回体，具体json格式如下

```json
{
  "success": true,
  "errorCode":1234566,
  "errorMsg": "",
  "result": {}
}
```

响应各字段的含义如下表所示

| 名称      | 类型    | 描述     |
| --------- | ------- | -------- |
| success   | Boolean | 是否成功 |
| errorCode | Long    | 错误码   |
| errorMsg  | String  | 错误信息 |
| result    | Object  | 响应结果 |

#### 2.错误码汇总

| 错误码     | 错误描述             |
| ---------- | -------------------- |
| 2000011005 | 令牌错误             |
| 2000061001 | 流程不存在           |
| 2000061003 | 流程未启用，无法调用 |
| 1          | 系统错误             |

## 二.授权机制

Juggle的OpenApi提供了两种令牌传递的方式，方便不同的业务场景使用

#### 1.通过请求头（Juggle-Token）传递

Juggle提供的各个语言的SDK都是通过传递，通过请求头传递的好处是业务系统无感，无侵入

#### 2.通过参数（juggleToken）传递

通过参数传递主要是通过Juggle设计的流程，能非常方便的作为其他系统webhook使用



## 三.api列表

#### 1.触发流程

**接口**

| Path         | /v1/open/flow/trigger/{flowVersion}/{flowKey} |
| ------------ | --------------------------------------------- |
| Method       | GET                                           |
| Content-Type | application/json                              |

**入参**

| 名称              | 类型   | 是否必填 | 描述                                                  |
| ----------------- | ------ | -------- | ----------------------------------------------------- |
| flowVersion       | String | 必填     | 流程版本                                              |
| flowKey           | String | 必填     | 流程Key                                               |
| juggleToken       | String | 非必填   | OpenApi的令牌值，可以通过参数传递，可以通过请求头传递 |
| 流程定义的入参Key | String | 非必填   | 流程定义中的入参，如果流程没有入参，可以不填          |

**出参**

| 名称      | 类型    | 描述                     |
| --------- | ------- | ------------------------ |
| success   | Boolean | 是否成功                 |
| errorCode | Long    | 错误码                   |
| errorMsg  | String  | 错误信息                 |
| result    | Object  | 流程定义中设置的出参结果 |

**示例**

下面以系统内置示例流程为例，展示调用方式

```
http://localhost:9127/v1/open/flow/trigger/v1/sync_example?juggleToken=eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcxMjE2MTIxNDAzOH0=&userName=juggle&password=123456&deposit=1000.00
```

![](/juggle/images/guide/integration/openapi_trigger_flow.png)


#### 2.获取异步流程结果

**接口**

| Path         | /v1/open/flow/getAsyncFlowResult/{flowInstanceId} |
| ------------ | ------------------------------------------------- |
| Method       | GET                                               |
| Content-Type | application/json                                  |

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
| result    | Object  | 流程定义中设置的出参结果 |

**示例**

```
http://localhost:9127/v1/open/flow/getAsyncFlowResult/async_PdCPkVCdgpyAB2e0?juggleToken=eyJ1c2VySWQiOjEsInRpbWVzdGFtcCI6MTcxMjE2MTIxNDAzOH0=
```


![](/juggle/images/guide/integration/openapi_get_flow_result.png)