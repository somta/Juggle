FROM registry.cn-heyuan.aliyuncs.com/somta/base-image:openjdk-1.8-v1

MAINTAINER Gavin somta@qq.com

ADD console/target/*.jar juggle-server.jar

EXPOSE 9127

ENV BASE_DIR="/home/juggle" \
    JVM_XMS="1g" \
    JVM_XMX="1g" \
    JVM_MS="128m" \
    JVM_MMS="320m" \
    JVM_GC_LOG="false" \
    TIME_ZONE="Asia/Shanghai"

COPY console/src/main/resources/data/application-default.properties /home/juggle/conf/application.properties
COPY console/src/main/resources/data/db_juggle.mv.db /home/juggle/data/
COPY console/src/main/resources/data/docker-startup.sh /home/juggle/bin/

ENTRYPOINT ["sh","/home/juggle/bin/docker-startup.sh"]