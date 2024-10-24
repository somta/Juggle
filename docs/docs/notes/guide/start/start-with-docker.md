---
title: Juggle Docker 快速开始
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/start/start-with-docker/
---
#  Juggle Docker 快速开始

## 一.直接docker命令运行

直接在支持docker的环境运行如下命令就可以启动Juggle

```shell
docker run --name juggle -d -p 9127:9127 somta/juggle:latest
```

## 二.使用docker-compose运行

### 1.创建一个docker-compose.yml文件，文件内容如下

```yaml
version: '3'
services:
  juggle:
    image: somta/juggle:latest
    container_name: juggle
    ports:
      - "9127:9127"
    volumes:
      - ./conf/application.properties:/home/juggle/conf/application.properties
```

> 建议将配置文件外挂，启动的时候通过volumes挂载，这样更方便后续维护

### 2.在docker-compose.yml的目录下新建一个conf目录，下面新建一个application.properties配置文件

```properties
server.port=9127
spring.application.name=juggle

##H2##
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:file:/data/db_juggle;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;IGNORECASE=TRUE;AUTO_SERVER=TRUE;OLD_INFORMATION_SCHEMA=TRUE
spring.datasource.username=sa
spring.datasource.password=juggle
spring.h2.console.enabled=true
spring.h2.console.settings.web-allow-others=true
spring.h2.console.path=/h2-console

##MySql##
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.url=jdbc:mysql://127.0.0.1:3306/juggle?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false
#spring.datasource.username=root
#spring.datasource.password=123456

##Redis Cache##
#juggle.cache.cache-type="redis"
#juggle.cache.redis.model="single"
#juggle.cache.redis.address=127.0.0.1:6379
#juggle.cache.redis.password=
```

### 3.启动Juggle

```
docker-compose up -d
```

