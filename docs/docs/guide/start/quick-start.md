#  Juggle快速开始

这个快速开始手册是帮忙您快速在您的电脑上，下载、安装并使用 Juggle。

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

**c.ubuntu启动**

```
bash startup.sh
```

### 4.访问Juggle

启动成功后，浏览器输入http://127.0.0.1:8686访问Juggle，默认登录信息 账号：juggle 密码：juggle

### 5.关闭服务器

**a.window关闭**

双击shutdown.cmd运行文件

**b.Linux/Unix/Mac关闭**

```
sh shutdown.sh
```

**c.ubuntu关闭**

```
bash shutdown.sh
```

