---
title: 流程
description: Juggle的流程是流程定义部署后的产物，流程支持多版本，可以实现流程的灰度和快速回滚等能力。
keywords:
  - 流程
  - Juggle流程
  - 流程示例
  - 微服务编排流程
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/user/flow/
---

# 流程

流程是由流程定义部署后的产物，具体部署过程可以参考[流程定义部署](/docs/guide/user/flow-definition/#部署流程)，但是Juggle并没有让流程作为最终的运行实体，因为实际应用场景下如果将流程作为最终的运行实体就会面临以下问题：

1.我刚才部署的问题真实运行的时候发现有问题，怎么快速回滚。

2.旧流程不能下线，但是新流程我要先正式验证一下，然后慢慢切换，类似灰度的过程。

因此Juggle引入了[流程版本](/docs/guide/user/flow-version/)的概念，让[流程版本](/docs/guide/user/flow-version/)做最终运行的实体，以此解决上面的问题。一个流程下会存在多个流程版本。

:::info
同一个流程下的多个流程版本的流程类型一定是相同的。
:::

