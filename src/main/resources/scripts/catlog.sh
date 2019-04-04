#!/bin/sh
source /etc/profile
## java env
#export JAVA_HOME=/usr/local/java/jdk1.7.0_72
#export JRE_HOME=$JAVA_HOME/jre

## service name
#########################      需要修改   ######################################
SERVICE_NAME=JfshareMvpServerPro-1.0.0
APP_NAME=com.jfshare.JfshareMvpServerPro


########################下面不需要修改######################################
SERVICE_DIR=../log

tail -f -n150 ${SERVICE_DIR}/nohup_$SERVICE_NAME.out
