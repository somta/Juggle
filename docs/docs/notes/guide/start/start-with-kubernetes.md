---
title: Juggle Kubernetes 快速开始
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/start/start-with-kubernetes/
---
#  Juggle Kubernetes 快速开始

### 1.新建ConfigMap

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: juggle-configmap
data:
  application.properties: |-
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

将上述的ConfigMap的内容保存为juggle-configmap.yaml文件，并通过下面的命令创建ConfigMap

```shell
kubectl apply -f juggle-configmap.yaml
```

### 2.新建Service

```yaml
apiVersion: v1
kind: Service
metadata:
  name: juggle-service
spec:
  selector:
    app: juggle
  ports:
    - protocol: TCP
      port: 9127
      targetPort: 9127
```

将上述的Service的内容保存为juggle-service.yaml文件，并通过下面的命令创建Service

```shell
kubectl apply -f juggle-service.yaml
```

### 3.新建Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: juggle-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: juggle
  template:
    metadata:
      labels:
        app: juggle
    spec:
      containers:
        - name: juggle
          image: somta/juggle:latest
          ports:
            - containerPort: 9127
          volumeMounts:
            - name: config-volume
              mountPath: /data/application.properties
              subPath: application.properties
      volumes:
        - name: config-volume
          configMap:
            name: juggle-configmap
```

将上述的Deployment的内容保存为juggle-deployment.yaml文件，并通过下面的命令创建Deployment

```shell
kubectl apply -f juggle-deployment.yaml
```