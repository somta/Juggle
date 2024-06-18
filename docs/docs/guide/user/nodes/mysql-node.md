---
description: 通过MySql节点，用户可以在MySql节点中输入不同的SQL，这些SQL会通过数据源直接在对应的数据源中执行，MySql节点让编排变得更加灵活。
keywords: [流程节点, MySql节点, 数据源,动态SQL]
---

# MySql节点



### 新建MySql节点
1.点击“+”，选择判断节点

2.在代码节点页面，输入**节点名称**，**节点描述**等信息





注意:如果引入的变量是一个字符串时，如下面的例子中name是一个字符串，就需要在变量外面加上单引号
INSERT INTO t_user (age, name) VALUES (${input_age}, '${input_name}');