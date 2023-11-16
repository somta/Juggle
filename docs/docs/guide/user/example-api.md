# 示例接口
为了让您更快的上手和使用Juggle的编排能力，Juggle为您提供了一些内置的示例接口，通过这些接口可以快速使用Juggle搭建一些具体的使用场景

### 一.用户示例接口

#### 1.用户登录

url：/example/user/login

Method： POST

Content-Type: application/json

**请求参数**

| 参数名称 | 类型   | 是否必填 | 默认值 |
| -------- | ------ | -------- | ------ |
| userName | String | 是       |        |
| password | String | 是       |        |

**响应结果**

| 名称     | 类型    | 默认值 |
| -------- | ------- | ------ |
| id       | Integer |        |
| name     | String  |        |
| age      | Integer |        |
| birthday | Date    |        |

#### 1.获取用户信息

url：/example/user/getUserById

Method： GET

Content-Type: application/json

**请求参数**

| 参数名称 | 类型    | 是否必填 | 默认值 |
| -------- | ------- | -------- | ------ |
| id       | Integer | 是       |        |

**响应结果**

| 名称     | 类型    | 默认值 |
| -------- | ------- | ------ |
| id       | Integer |        |
| name     | String  |        |
| age      | Integer |        |
| birthday | Date    |        |











### 二.订单示例接口