---
description: Juggle提供了一些示例接口，这让用户能更快更简单的上手Juggle，解决用户上手困难的问题。
keywords: [Juggle示例接口, mock接口, 接口例子]
---

# 示例接口
为了让您更快的上手和使用Juggle的编排能力，Juggle为您提供了一些内置的示例接口，通过这些接口可以快速使用Juggle搭建一些具体的使用场景

### 一.用户示例接口

#### 1.用户登录

url：/example/user/login

Method： POST

Content-Type: application/json

**请求参数**

| 参数名称 | 类型   | 是否必填 | 默认值 | 描述                        |
| -------- | ------ | -------- | ------ | --------------------------- |
| userName | String | 是       |        | 用户名称 mock正确值：juggle |
| password | String | 是       |        | 密码  mock正确值：123456    |

**请求参数示例**

```json
{
  "userName": "juggle",
  "password": "123456"
}
```

**响应结果**

| 名称      | 类型    | 默认值 | 描述                           |
| --------- | ------- | ------ | ------------------------------ |
| userId    | Integer |        | 用户ID                         |
| userName  | String  |        | 用户名称                       |
| loginFlag | Boolean |        | true：登录成功  false:登录失败 |

**响应结果示例**

```json
{
  "userId": 1,
  "userName": "juggle",
  "loginFlag": true
}
```



#### 2.获取用户信息

url：/example/user/getUserById

Method： GET

Content-Type: application/x-www-form-urlencoded

**请求参数**

| 参数名称 | 类型    | 是否必填 | 默认值 | 描述   |
| -------- | ------- | -------- | ------ | ------ |
| userId   | Integer | 是       |        | 用户ID |

**响应结果**

| 名称     | 类型    | 默认值 | 描述     |
| -------- | ------- | ------ | -------- |
| id       | Integer |        | 用户ID   |
| name     | String  |        | 用户名称 |
| age      | Integer |        | 用户年龄 |
| birthday | Date    |        | 用户生日 |

**响应结果示例**

```json
{
  "id": 1,
  "name": "张三",
  "age": 18,
  "birthday": "2023-11-19"
}
```



### 二.商品示例接口

#### 1.发布商品

url：/example/goods/releaseGoods

Method： GET

Content-Type: application/json

**请求参数**

| 参数名称       | 类型    | 是否必填 | 默认值 | 描述     |
| -------------- | ------- | -------- | ------ | -------- |
| goodsName      | String  | 是       |        | 商品名称 |
| goodsInventory | Integer | 否       |        | 商品库存 |

**请求参数示例**

```json
{  
   "goodsName": "鞋",
   "goodsInventory": 20
}
```

**响应结果**

| 名称           | 类型    | 默认值 | 描述     |
| -------------- | ------- | ------ | -------- |
| goodsId        | Integer  |        | 商品ID   |
| goodsName      | String |        | 商品名称 |
| goodsInventory | Integer |        | 商品库存 |

**响应结果示例**

```json
{  
   "goodsId": 999,
   "goodsName": "鞋",
   "goodsInventory": 20
}
```



#### 2.获取商品详情

url：/example/goods/getGoodsInfo

Method： GET

Content-Type: application/json

**请求参数**

| 参数名称       | 类型    | 是否必填 | 默认值 | 描述     |
| -------------- | ------- | -------- | ------ | -------- |
| goodsName      | String  | 是       |        | 商品名称 |
| goodsInventory | Integer | 否       |        | 商品库存 |

**请求参数示例**

```json
{  
   "goodsName": "鞋",
   "goodsInventory": 20
}
```

**响应结果**

| 名称           | 类型    | 默认值 | 描述     |
| -------------- | ------- | ------ | -------- |
| goodsId        | Integer  |        | 商品ID   |
| goodsName      | String |        | 商品名称 |
| goodsPrice     | Double  |        | 商品单价 |
| goodsInventory | Integer |        | 商品库存 |

**响应结果示例**

```json
{  
   "goodsId": 999,
   "goodsName": "鞋",
   "goodsPrice": 189.59,
   "goodsInventory": 20
}
```



### 三.订单示例接口

#### 1.下单

url：/example/order/placeOrder

Method： POST

Content-Type: application/x-www-form-urlencoded

**请求参数**

| 参数名称  | 类型    | 是否必填 | 默认值 | 描述     |
| --------- | ------- | -------- | ------ | -------- |
| orderName | String  | 否       |        | 订单名称 |
| userId    | Integer | 是       |        | 用户ID   |

**请求参数示例**

```json
{ 
   "orderName": "测试订单",
   "userId": 1
}
```

**响应结果**

| 名称      | 类型    | 默认值 | 描述     |
| --------- | ------- | ------ | -------- |
| orderNo   | String  |        | 订单号   |
| orderName | String  |        | 订单名称 |
| userId    | Integer |        | 用户ID   |

**响应结果示例**

```json
{  
   "orderNo": "NO123",
   "orderName": "这是一个测试订单",
   "userId": 1
}
```



#### 2.获取订单详情

url：/example/order/getOrderByNo

Method： GET

Content-Type: application/x-www-form-urlencoded

**请求参数**

| 参数名称 | 类型   | 是否必填 | 默认值 | 描述   |
| -------- | ------ | -------- | ------ | ------ |
| orderNo  | String | 是       |        | 订单号 |

**响应结果**

| 名称      | 类型    | 默认值 | 描述     |
| --------- | ------- | ------ | -------- |
| orderNo   | String  |        | 订单号   |
| orderName | String  |        | 订单名称 |
| userId    | Integer |        | 用户ID   |

**响应结果示例**

```json
{  
   "orderNo": "NO123",
   "orderName": "测试订单",
   "userId": 1
}
```



#### 3.获取订单列表

url：/example/order/getUserOrderList

Method： GET

Content-Type: application/x-www-form-urlencoded

**请求参数**

| 参数名称  | 类型    | 是否必填 | 默认值 | 描述     |
| --------- | ------- | -------- | ------ | -------- |
| orderName | String  | 否       |        | 订单名称 |
| userId    | Integer | 是       |        | 用户ID   |

**响应结果**

| 名称        | 类型    | 默认值 | 描述     |
| ----------- | ------- | ------ | -------- |
| userId      | Integer |        | 用户ID   |
| orderList   | List    |        | 订单列表 |
| - orderNo   | String  |        | 订单号   |
| - orderName | String  |        | 订单名称 |
| - userId    | Integer |        | 用户ID   |

**响应结果示例**

```json
{
  "userId": 1,
  "orderList": [
    {
      "orderNo": "NO123",
      "orderName": "测试订单",
      "userId": 1
    },
    {
      "orderNo": "NO456",
      "orderName": "测试订单",
      "userId": 1
    }
  ]
}
```

