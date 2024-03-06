![Static Badge](https://img.shields.io/badge/Jdk-1.8%2B-orange)
![Static Badge](https://img.shields.io/badge/Maven-3.5.x-blue)
![Static Badge](https://img.shields.io/badge/SpringBoot-2.7.14-green)
![Static Badge](https://img.shields.io/badge/Vue-3.x-purple)

# Juggle
## 项目介绍
Juggle是一个接口编排的低代码工具，通过它可以快速将简单的API编排成一个复杂的接口，编排的接口可以直接给前端使用，极大的提高开发效率，减轻开发成本。

## 什么时候需要Juggle
1.适合已有基础服务能力，通过Juggle进行编排编排快速搭建一个新产品。

2.系统需要与第三方系统进行对接，通过Juggle直接进行编排，无需任何开发就可以完成对接。

3.适合做前端的适配层（即BFF），可以用Juggle替代常见的通过Nodejs来实现BFF层的能力。

4.适合需要面向私有化或大量定制开发的产品，通过Juggle编排定制化接口，避免对标准代码的污染。


## 快速开始

### 1.环境准备

Juggle依赖Java环境来运行，因此您先要在设备上安装jdk，请保证是在以下版本环境中安装使用：

a. 64 bit OS，支持 Linux/Unix/Mac/Windows，推荐选用 Linux/Unix/Mac。

b. 64 bit JDK 1.8+；[下载地址](https://maven.apache.org/download.cgi) & [配置](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)。

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

启动成功后，浏览器输入http://127.0.0.1:8686访问Juggle，默认登录信息 账号：juggle 密码：juggle

## 感恩与支持
感谢为Juggle功能持续更新日夜奋战的小伙伴们，感谢为项目提出宝贵优化意见的大佬们！
     
     动动您发财的手，点个Star，是对我们更新最大的支持！
