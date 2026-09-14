![Static Badge](https://img.shields.io/badge/Jdk-21-orange)
![Static Badge](https://img.shields.io/badge/Maven-3.5.x-blue)
![Static Badge](https://img.shields.io/badge/SpringBoot-3.5.x-green)
![Static Badge](https://img.shields.io/badge/Vue-3.x-purple)

# Juggle

## 项目介绍
Juggle是一个接口编排的低代码工具，通过它可以快速将简单的API编排成一个复杂的接口，编排的接口可以直接给前端使用，极大的提高开发效率，减轻开发成本。

## 演示环境
Juggle官方文档地址: https://juggle.plus

Juggle演示环境地址: https://demo.juggle.plus/#/login (演示环境可以使用gitee或github登录体验)


## 什么时候需要Juggle
1.适合已有基础服务能力，通过Juggle进行微服务接口编排快速搭建一个新产品。

2.系统需要与第三方系统进行对接，通过Juggle直接进行编排，无需任何开发就可以完成对接。

3.适合做前端的适配层（即BFF），可以用Juggle替代常见的通过Nodejs来实现BFF层的能力。

4.适合需要面向私有化或大量定制开发的产品，通过Juggle编排定制化接口，避免对标准代码的污染。

## 功能特性
1.流程多版本管理，天然支持流程灰度能力

2.支持字符串，布尔，整数，小数，日期，时间，列表，对象等数据结构，满足绝大数数据定义场景

3.内置方法节点，判断节点，代码节点，赋值节点，MySql节点，并行节点，缓存节点等多种节点，能灵活设计流程

4.支持Groovy,JavaScript,Python,Java等多种脚本语言来增强流程

5.支持Http,Dubbo,WebService等协议的接口调用 

6.套件市场拥有几十个常见系统的官方套件（如：通义千问，钉钉机器人，QQ邮箱，阿里云短信）等，开箱即用，大大降低流程设计的复杂度

7.全信创支持，支持MySql，达梦，TiDB，OceanBase，Doris等近十款数据库


## 流程说明
Juggle支持创建多种不同类型的流程，不同类型的流程解决不同类型的业务场景，下面将对每种流程的详细介绍

### 1.同步流程

**描述：** 即流程的执行结果是同步的返回，调用方能直接获取流程结果

**场景：** 对流程实时性要求比较高的场景，如：微服务接口编排，BFF层接口等


### 2.异步流程

**描述：** 即流程的执行结果是异步的返回，调用方需要通过另外的接口获取异步流程的结果

**场景：** 对流程实时性要求不高或者流程执行比较耗时的场景，如：数据清洗等


### 3.定时流程

**描述：** 即不太关心流程的执行结果，需要定时定点或周期性的执行流程

**场景：** 对流程实时性要求不高或者需要定时定点周期性执行的场景，如：数据定时同步等


### 4.监听流程

**描述：** 即不太关心流程的执行结果，需要监听业务系统MQ的消息来执行流程

**场景：** 需要直接监听业务系统MQ消息后出现业务逻辑的场景，如：数据清洗，数据标注等

## 节点说明
Juggle支持丰富的节点类型，通过不同节点的组装能解决几乎所有的接口编排的场景，具体节点如下

### 方法节点
方法节点是用来承载接口的，在该节点中可以选择套件下定义好的接口，然后通过变量给接口请求头或入参赋值，并将需要的接口出参赋值给变量，供流程内其他节点使用，通过多个系统接口的编排可以打通系统之间的屏障。

### 并行节点
并行节点主要是允许用户在一个节点上同时添加多个接口，同时允许指定多个接口的执行策略，当使用并行执行策略时，会启用多个虚拟线程来调用接口，在同时需要调用多个接口的场景，并且需要较高性能的情况下非常有用。

### 判断节点
判断节点主要是允许用户定义多个判断条件的，一个判断节点会有多个分支，默认会有一个else分支，每个分支之间是互斥的，从左往右依次执行，主要处理流程中需要通过不同条件进行不同业务逻辑的场景使用。

### 循环节点
循环节点主要是解决流程中需要循环执行的场景，如循环查询数据，上一个节点的一个集合数据，需要进行一些处理后，插入到数据库的不同表中的场景，在循环节点内部也能添加不同的节点，通过这些节点来完成循环的能力。

### 赋值节点
赋值节点主要是来用户根据业务场景的需求，在不同的分支条件下对同一变量赋予不同的值，以满足不同的业务场景的赋值的需要。

### 调用节点
调用节点主要是用来调用子流程，用户可以在该节点中指定一个已经编辑好的子流程，抽离出公共的流程，保证流程的复用性。

### 代码节点
代码节点主要是来用户自定义的代码，即可以在流程设计过程中添加一个代码节点，可以在该节点中书写Groovy，JavaScript，Python，Java等代码，通过这些代码完成变量的组装转换，业务逻辑增强，数据清洗等工作，代码节点将极大的提升Juggle编排的灵活性

### 队列节点
队列节点主要是用来在流程中对接各种消息队列，用户能将指定的数据发送到消息队列中，让流程也具备解耦和异步的能力，在高并发场景下非常实用。

### 数据库节点
数据库节点主要是用来在流程中直接连接数据库，在没有接口的情况下，直接通过连接几十种数据源，书写对应SQL的方式，完成对数据的处理，在数据清洗，没有接口的老系统，大数据场景下非常有用。

### 缓存节点
缓存节点主要是用来在流程中进行数据的全局缓存，如将接口返回的数据缓存，减少接口的压力，极大的提升了流程的整体性能。

## 系统截图

1.灵活流程设计
![](https://juggle.plus/images/flow_config.png)

2.丰富的套件市场
![](https://juggle.plus/images/suite_market.png)


## 快速开始

### 1.环境准备

Juggle依赖Java环境来运行，因此您先要在设备上安装jdk，请保证是在以下版本环境中安装使用：

a. 64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。

b. 64 bit JDK 21；[jdk-21下载地址](https://www.oracle.com/java/technologies/downloads/#java21) & [配置](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)。

### 2.下载安装包

您可以从 [最新稳定版本](https://github.com/somta/Juggle/releases) 下载 `juggle-server-$version.zip` 包，window下直接通过解压工具解压`juggle-server-$version.zip`，Linux/Unix/Mac通过如下命令解压

```
tar -xvf juggle-server-$version.tar.gz
```

### 3.启动服务器

启动脚本在juggle/bin目录

**a.window启动**

双击startup.cmd运行文件

**b.Linux/Unix/Mac启动**

```
sh startup.sh
```

### 4.访问Juggle

启动成功后，浏览器输入http://127.0.0.1:9127访问Juggle，默认登录信息 账号：juggle 密码：juggle

### 5.示例流程

为了让用户更好的上手Juggle，系统自带了示例接口和示例流程，通过示例流程能快速了解Juggle的基础能力，示例流程核心逻辑请移步[示例流程核心逻辑](https://www.juggle.plus/docs/guide/user/example-flow) ，示例流程图如下：
![](/docs/images/flow_example.png)


示例接口地址：https://www.juggle.plus/docs/guide/user/example-api.html

示例流程地址：https://www.juggle.plus/docs/guide/user/example-flow.html

## 交流与学习
通过如下方式加入，学习更多关于Juggle的知识，添加微信时，请备注**Juggle**，谢谢！

![](/docs/images/wxqq.png) 

## 客户与案例
<div align = "center"> 
    <img src="https://juggle.plus/customer/hstong.png" alt="" width="33%" style="background-color: #383434"/>
    <img src="https://juggle.plus/customer/pingankeji.png" alt="" width="33%" />
    <img src="https://juggle.plus/customer/megvii.png" alt="" width="33%" />
    <img src="https://juggle.plus/customer/swsc.png" alt="" width="33%" />
    <img src="https://juggle.plus/customer/xinyucores.png" alt="" width="33%" style="background-color: #000"/>
    <img src="https://juggle.plus/customer/scooper.png" alt="" width="33%" />
</div>

## 感恩与支持
感谢为Juggle功能持续更新日夜奋战的小伙伴们，感谢为项目提出宝贵优化意见的大佬们！
     
     动动您发财的手，点个Star，是对我们更新最大的支持！
