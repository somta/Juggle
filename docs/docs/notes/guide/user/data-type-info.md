---
title: 支持的数据类型
description: Juggle支持丰富的数据类型，为不同字段定义合适的数据类型，能更加方便的编排数据和接口。
keywords:
  - 字段数据类型
  - 变量表达式
  - 变量引擎
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/user/data-type-info/
---

# 支持的数据类型

### 数据类型

数据类型用于指定接口的出入参类型，变量类型，目前数据类型分为三大类：基础类型，集合类型，对象类型，每种大类下的具体类型如下表所示：

| 数据类型分类 | 数据类型      |
| ------------ |-----------|
| 基础类型     | 字符串       |
|              | 整数        |
|              | 小数        |
|              | 布尔        |
|              | 日期        |
|              | 时间        |
| 集合类型     | 集合        |
| 对象类型     | 订单（用户自定义） |
|              | 商品（用户自定义） |

### 数据类型支持的表达式

每一种数据类型支持的表达式是不一样的，这个是由数据类型的格式和形态决定的，下面的表格列出了Juggle每种数据类型支持的表达式。

| 数据类型分类 | 数据类型           | 表达式   | 示例                                         | 示例说明                                    |
| ------------ | ------------------ | -------- | ------------------------------------------ | ------------------------------------------- |
| 基础类型     | 字符串             | 等于     | env_name=="zhansan"                        | 变量env_name等于zhansan                     |
|              |                    | 不等于   | env_name!="zhansan"                        | 变量env_name不等于zhansan                   |
|              |                    | 为空     | string.empty(env_name)                     | 变量env_name为空                            |
|              |                    | 不为空   | !string.empty(env_name)                    | 变量env_name不为空                          |
|              |                    | 包含     | string.contains(s1,s2)                     | 字符串s1包含字符串s2                        |
|              |                    | 不包含   | !string.contains(s1,s2)                    | 字符串s1不包含字符串s2                      |
|              |                    |          |                                            |                                             |
|              | 整数               | 等于     | env_age==18                                | 变量env_age等于18                           |
|              |                    | 不等于   | env_age!=18                                | 变量env_age不等于18                         |
|              |                    | 大于     | env_age>18                                 | 变量env_age大于于18                         |
|              |                    | 大于等于 | env_age>=18                                | 变量env_age大于等于18                       |
|              |                    | 小于     | env_age<18                                 | 变量env_age小于18                           |
|              |                    | 小于等于 | env_age<=18                                | 变量env_age小于等于18                       |
|              |                    |          |                                            |                                             |
|              | 小数               | 等于     | env_money==100.23                          | 变量env_money等于100.23                     |
|              |                    | 不等于   | env_money!=100.23                          | 变量env_money不等于100.23                   |
|              |                    | 大于     | env_money>100.23                           | 变量env_money大于100.23                     |
|              |                    | 大于等于 | env_money>=100.23                          | 变量env_money大于等于100.23                 |
|              |                    | 小于     | env_money<100.23                           | 变量env_money小于100.23                     |
|              |                    | 小于等于 | env_money<=100.23                          | 变量env_money小于等于100.23                 |
|              |                    |          |                                            |                                             |
|              | 布尔               | 等于     | env_is_login==true                         | 变量env_is_login为true                      |
|              |                    | 不等于   | env_is_login!=true                         | 变量env_is_login不为true                    |
|              |                    |          |                                            |                                             |
|              | 时间               | 等于     | date.eq(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday等于2023-12-13 18:14:34     |
|              |                    | 不等于   | !date.eq(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday不等于2023-12-13 18:14:34   |
|              |                    | 大于     | date.gt(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday大于2023-12-13 18:14:34     |
|              |                    | 大于等于 | date.ge(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday大于等于2023-12-13 18:14:34 |
|              |                    | 小于     | date.lt(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday小于2023-12-13 18:14:34     |
|              |                    | 小于等于 | date.le(env_birthday,'2023-12-13 18:14:34') | 变量env_birthday小于等于2023-12-13 18:14:34 |
|              | 枚举               |          |                                            |                                             |
|              |                    |          |                                            |                                             |
|              |                    |          |                                            |                                             |
| 集合类型     | 集合               | 为空     | list.empty(env_userList)                   | 用户列表为空                                |
|              |                    | 不为空   | !list.empty(env_userList)                  | 用户列表不为空                              |
|              |                    |          |                                            |                                             |
| 对象类型     | 订单（用户自定义） | 为空     |                                            |                                             |
|              |                    | 不为空   |                                            |                                             |

