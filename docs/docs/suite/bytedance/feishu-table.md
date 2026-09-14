---
title: 飞书多维表格
description: 飞书多维表格是一款强大的在线数据协作与管理工具，它将传统表格的易用性与数据库的强大功能相结合，让团队无需编写代码即可快速搭建灵活、智能的业务管理系统
keywords:
  - 飞书
  - 多维表格
permalink: /suite/bytedance/table.html
createTime: 2025/09/18 18:29:05
---

飞书多维表格套件是官方推出的一个套件，飞书多维表格是一款强大的在线数据协作与管理工具，它将传统表格的易用性与数据库的强大功能相结合，让团队无需编写代码即可快速搭建灵活、智能的业务管理系统，在Juggle中可以通过飞书多维表格的存储能力，将流程中的数据自动写入到多维表格中，达到轻量存储的目的。




## 如何获取多维表格的appId和appSecret

如果想通过API来操作飞书多维表格的能力，就需要前往[飞书开放平台](https://open.feishu.cn/)，创建应用并授权后才能获取可以使用的appId和appSecret来操作多维表格，具体步骤如下

#### 1.创建一个应用
![创建应用](images/feishu_app.png)

2.为应用配置相关的权限



3.发布应用



4.在飞书多维表格中添加应用



## 如何获取飞书多维表格的appToken

- 如果多维表格的 URL 以 **feishu.cn/base** 开头，该多维表格的 appToken 是下图高亮部分：![app_token.png](https://sf3-cn.feishucdn.com/obj/open-platform-opendoc/6916f8cfac4045ba6585b90e3afdfb0a_GxbfkJHZBa.png?height=766&lazyload=true&width=3004)
- 如果多维表格的 URL 以 **feishu.cn/wiki** 开头，你需调用知识库相关[获取知识空间节点信息](https://open.feishu.cn/document/ukTMukTMukTM/uUDN04SN0QjL1QDN/wiki-v2/space/get_node)接口获取多维表格的 appToken。当 `obj_type` 的值为 `bitable` 时，`obj_token` 字段的值才是多维表格的 `app_token`。

了解更多关于获取appToken的方式，参考[多维表格 app_token 获取方式](https://open.feishu.cn/document/ukTMukTMukTM/uUDN04SN0QjL1QDN/bitable-overview#-752212c)。

**示例值**："bascng7vrxcxpig7geggXiCtadY"



## 如何获取飞书多维表格的tableId


