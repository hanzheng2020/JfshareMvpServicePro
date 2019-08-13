#!/bin/sh
source /etc/profile
## java env
#export JAVA_HOME=/usr/local/java/jdk1.7.0_72
#export JRE_HOME=$JAVA_HOME/jre

## service name
#########################    change   ######################################
SERVICE_NAME=JfshareMvpServerPro-1.0.0
APP_NAME=com.jfshare.JfshareMvpServerPro


###################     do not change   ###################


SERVICE_DIR=../log

JAR_NAME=$SERVICE_NAME.jar
PID=$SERVICE_NAME\.pid

#cd $SERVICE_DIR

case "$1" in

    startShell)
            echo "=== start shell $SERVICE_NAME"
            java -Xmx512M -Xms256M -Dspring.profiles.active=dev -jar  $SERVICE_NAME.jar
            ;;

       start)
            echo "=== start $SERVICE_NAME"
            supervisorctl start bonus-points-wechat
            ;;

        stop)
            echo "=== stop $SERVICE_NAME"
            supervisorctl stop bonus-points-wechat
            ;;

        restart)
            $0 stop
            sleep 3
            $0 start
            echo "=== restart $SERVICE_NAME"
            ;;

        *)
            ## restart
            $0 stop
            sleep 3
            $0 start
            ;;
esac
exit 0
