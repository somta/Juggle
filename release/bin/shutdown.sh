#!/bin/bash

cd `dirname $0`/../target
target_dir=`pwd`

pid=`ps ax | grep -i 'juggle.juggle' | grep ${target_dir} | grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "No juggleServer running."
        exit -1;
fi

echo "The juggleServer(${pid}) is running..."

kill ${pid}

echo "Send shutdown request to juggleServer(${pid}) OK"
