---
title: 示例流程
description: Juggle提供了一些示例流程，这让用户能更快更简单的上手Juggle，解决用户上手困难的问题。
keywords:
  - Juggle示例流程
  - mock接口
  - 接口例子
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/user/example-flow/
---

# 示例流程
为了让您更快的上手和使用Juggle的编排能力，Juggle为您提供了一些内置的示例接口，并通过这些示例接口，搭建了“**用户登录,根据用户信息自动送商品**”的示例流程

### 流程出入参

#### 1.流程入参

| 参数名称 | 类型   | 是否必填 | 描述                        |
| -------- | ------ | -------- | --------------------------- |
| userName | String | 是       | 用户名称 mock正确值：juggle |
| password | String | 是       | 密码  mock正确值：123456    |
| deposit  | Double | 否       | 存款余额                    |

#### 2.流程出参

| 参数名称  | 类型    | 描述     |
| --------- | ------- | -------- |
| userName  | String  | 用户名称 |
| age       | Integer | 用户年龄 |
| orderName | String  | 订单名称 |

### 流程图

![](/juggle/images/guide/user/flow_example.png)

### 核心逻辑

1.用户输入账号和密码登录，如果账号密码错误，直接结束流程返回

2.如果存款余额小于10万，就给用户送50元话费

3.如果存款余额大于10万，就给用户送一双耐克鞋

4.流程返回用户姓名，用户年龄，订单名称

### 测试过程

1.输出错误的登录密码，不会送任何东西，直接返回
![](/juggle/images/guide/user/flow_example_1.png)

2.用户名和密码正确，存款金额为1000.00，返回的订单是送10元话费
![](/juggle/images/guide/user/flow_example_2.png)


3.用户名和密码正确，存款金额为300000.00，返回的订单是送一双耐克的鞋
![](/juggle/images/guide/user/flow_example_3.png)


