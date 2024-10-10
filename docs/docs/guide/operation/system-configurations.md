---
title: 系统参数
description: 如何快速介绍微服务编排框架Juggle？
keywords: [Juggle部署方案, Juggle部署, 部署方式]
---

Juggle提供了丰富的配置适配不同的场景，可以通过修改application.properties里面的配置项，下面会详细介绍每个配置的作用和配置方法

### 1.基础配置

| 参数名                  | 含义                 | 可选值 | 默认值 |
| ----------------------- | -------------------- | ------ | ------ |
| server.port             | Juggle Server 的端口 | 正整数 | 9127   |
| spring.application.name | Juggle Server 的名称 | 字符串 | juggle |

### 2.数据库配置

| 参数名                              | 含义             | 可选值               | 默认值           |
| ----------------------------------- | ---------------- | -------------------- | ---------------- |
| spring.datasource.driver-class-name | 数据库的驱动名称 | 所用数据库的驱动名称 | org.h2.Driver    |
| spring.datasource.url               | 数据库连接地址   | 所用数据库的连接地址 | ./data/db_juggle |
| spring.datasource.username          | 数据库账号       | 所用数据库的账号     | sa               |
| spring.datasource.password          | 数据库密码       | 所用数据库的密码     | juggle           |

### 3.缓存配置

| 参数名                             | 含义                                | 可选值                                                    | 默认值 |
| ---------------------------------- | ----------------------------------- | --------------------------------------------------------- | ------ |
| juggle.cache.cache-type            | 缓存类型                            | memory/redis                                              | memory |
| juggle.cache.redis.model           | 当使用redis作为缓存时，redis的模式  | single: 单机模式   sentinel: 哨兵模式   cluster: 集群模式 | single |
| juggle.cache.redis.address         | Redis的连接地址，多个地址用逗号隔开 | 字符串                                                    | null   |
| juggle.cache.redis.password        | Redis的密码                         | 字符串                                                    | null   |
| juggle.cache.redis.sentinel-master | Redis采用哨兵模式时，主服务器名称   | 字符串                                                    | null   |