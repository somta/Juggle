#!/bin/bash
# Copyright 2021-2025 somta.
# shell debug set -x
# set -x

function join_if_exist() {
    if [ -n "$2" ]; then
        echo "$1$2"
    else
        echo ""
    fi
}

#===========================================================================================
# JVM Configuration
#===========================================================================================
Xms=$(join_if_exist "-Xms" ${JVM_XMS})
Xmx=$(join_if_exist "-Xmx" ${JVM_XMX})
XX_MS=$(join_if_exist "-XX:MetaspaceSize=" ${JVM_MS})
XX_MMS=$(join_if_exist "-XX:MaxMetaspaceSize=" ${JVM_MMS})

JAVA_OPT="${JAVA_OPT} $Xms $Xmx $XX_MS $XX_MMS"
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"
if [ "${JVM_GC_LOG}" == "true" ]; then
  JAVA_OPT="${JAVA_OPT} -Xlog:gc*:file=${BASE_DIR}/logs/juggle_gc.log:time,tags:filecount=10,filesize=102400"
fi

#===========================================================================================
# Setting system properties
#===========================================================================================
JAVA_OPT="${JAVA_OPT} -Duser.timezone=${TIME_ZONE}"
JAVA_OPT="${JAVA_OPT} --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED"
JAVA_OPT="${JAVA_OPT} -jar /juggle-server.jar"
JAVA_OPT="${JAVA_OPT} --spring.config.additional-location=${BASE_DIR}/conf/application.properties"

echo "Juggle is starting, you can docker logs your container JAVA_OPT:  ${JAVA_OPT} "
exec java ${JAVA_OPT}