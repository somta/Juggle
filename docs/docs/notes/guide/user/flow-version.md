---
title: 流程版本
description: Juggle的流程版本是为了解决用户流程回滚和灰度发布等问题，帮助用户减轻流程切换的成本，提供流程的使用效率。
keywords:
  - 流程版本
  - 第三方系统集成
  - 微服务接口编排
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/user/flow-version/
---

# 流程版本

流程版本是流程运行的最终实体，流程设计完成后部署就会产生一个流程版本，每个流程版本无需用户手动输入，系统会自动增加版本号。

### 启用流程版本

1.在"**流程**" > "**流程列表**" > "**版本列表**"页面，点击“**启用**”按钮

2.在二次确认弹框中，再次点击“**确定**”按钮

:::info
启用后的流程版本才能被触发调用。
:::

### 禁用流程版本

1.在"**流程**" > "**流程列表**" > "**版本列表**"页面，点击“**禁用用*”按钮

2.在二次确认弹框中，再次点击“**确定**”按钮

:::info
禁用后的流程就无法调用了，调用的时候抛出“**请启用流程后在调用流程**”的提示。
:::

### 删除流程版本

1.在"**流程**" > "**流程列表**" > "**版本列表**"页面，点击“**删除**”按钮

2.在二次确认弹框中，再次点击“**确定**”按钮

:::info
启用中的流程版本是无法删除的，如果要删除需要确认没有系统调用后，先禁用流程版本，然后在删除该流程版本
:::