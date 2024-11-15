---
title: 部署手册概览
description: 如何快速介绍微服务编排框架Juggle？
keywords:
  - Juggle部署方案
  - Juggle部署
  - 部署方式
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/operation/deployment-overview/
---

### 1.Juggle支持的两种部署模式

单机模式 - 又称单例模式，主要用于测试和单机试用。

集群模式 - 主要用于生产环境，确保高可用。

#### a.单机模式

单机模式又称单例模式，拥有所有Juggle的功能及特性，具有极易部署，快速启动等优点。但是无法与其他Juggle实例组成集群，无法在节点或网络故障时提供高可用能力。单机模式同样可以使用内置H2数据库（默认）和外置数据库进行存储。



单机模式主要适合于工程师于本地搭建或于测试环境中搭建Juggle环境，主要用于开发调试及测试使用；也能够兼顾部分对稳定性和可用性要求不高的业务场景。



单机模式的部署参考文档: [单机模式部署](https://juggle.plus/docs/guide/operation/deployment-standalone)

#### b.集群模式

集群模式又称多例模式，通过将多个Juggle实例节点组成一个集群，集群模式具有高可用、高扩展、高并发等优点，确保在故障发生时不影响业务的运行。集群模式**必须采用**外置数据库和外置缓存进行存储。



该模式主要适合于生产环境，也是我们推荐的部署模式。



单机模式的部署参考文档: [集群模式部署](https://juggle.plus/docs/guide/operation/deployment-cluster)