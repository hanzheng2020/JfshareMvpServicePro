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

SERVICE_DIR=./log

JAR_NAME=$SERVICE_NAME.jar
PID=$SERVICE_NAME\.pid

#cd $SERVICE_DIR

case "$1" in

    start)
	nohup java -Xmx512M -Xms256M -Dspring.profiles.active=dev -jar  $SERVICE_NAME.jar >> nohup_$SERVICE_NAME.out 2>&1 &
        echo $! > $SERVICE_DIR/$PID
        echo "=== start $SERVICE_NAME"
        echo $SERVICE_DIR/$PID
        tail -f nohup_$SERVICE_NAME.out
        ;;

    stop)
        kill `cat $SERVICE_DIR/$PID`
        rm -rf $SERVICE_DIR/$PID
        echo "=== stop $SERVICE_NAME"

        sleep 2
        P_ID=`ps -ef | grep -w "$SERVICE_NAME" | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "=== $SERVICE_NAME process not exists or stop success"
        else
            echo "=== $SERVICE_NAME process pid is:$P_ID"
            echo "=== begin kill $SERVICE_NAME process, pid is:$P_ID"
            kill -9 $P_ID
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        echo "=== restart $SERVICE_NAME"
        ;;

    *)
        ## restart
        $0 stop
        sleep 2
        $0 start
        ;;
esac
exit 0

