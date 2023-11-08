@echo off

if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
set "JAVA=%JAVA_HOME%\bin\java.exe"

setlocal enabledelayedexpansion

set BASE_DIR=%~dp0
rem added double quotation marks to avoid the issue caused by the folder names containing spaces.
rem removed the last 5 chars(which means \bin\) to get the base DIR.
set BASE_DIR="%BASE_DIR:~0,-5%"

set CUSTOM_SEARCH_LOCATIONS=file:%BASE_DIR%/conf/

set SERVER=juggle-server

rem set juggle options
set "JUGGLE_JVM_OPTS=-Xms512m -Xmx512m -Xmn256m"
set "JUGGLE_OPTS=%JUGGLE_OPTS% -Djuggle.home=%BASE_DIR%"
set "JUGGLE_OPTS=%JUGGLE_OPTS% -jar %BASE_DIR%\target\%SERVER%.jar"

rem set juggle spring config location
set "JUGGLE_CONFIG_OPTS=--spring.config.additional-location=%CUSTOM_SEARCH_LOCATIONS%"

rem set juggle log4j file location
set "JUGGLE_LOG4J_OPTS=--logging.config=%BASE_DIR%/conf/juggle-logback.xml"

set COMMAND="%JAVA%" %JUGGLE_JVM_OPTS% %JUGGLE_OPTS% %JUGGLE_CONFIG_OPTS% %JUGGLE_LOG4J_OPTS% juggle.juggle %*

rem start nacos command
%COMMAND%
