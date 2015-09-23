#!/bin/bash

export REPOSITORY=/home/$USER/repository
export JAVA_HOME=/opt/java/jdk1.7.0_55
export TOMCAT_HOME=/opt/tomcat7
export WEBAPPS=/dyne/wwwroot
export APPBASE=$WEBAPPS/localhost
export BACKUP=/dyne/lost+found

cmd=$1
war_file=$2
deploy_mode=$3

stop() {
	cd $TOMCAT_HOME
	echo "check if tomcat is up"
	tomcat_pid=`ps -Nf | grep $TOMCAT_HOME | sed -n '1p' | awk '{print $war_file}'`
	tomcat_pid2=`ps -Nf | grep $TOMCAT_HOME | sed -n '1p' | awk '{print $war_file}'`
	if [[ $tomcat_pid = $tomcat_pid2 ]]
	then
	  echo "shut down tomcat"
	  bin/shutdown.sh

	  echo "wait till tomcat is down"
	  sleep 10

	  tomcat_pid=`ps -Nf | grep $TOMCAT_HOME | sed -n '1p' | awk '{print $war_file}'`
	  tomcat_pid2=`ps -Nf | grep $TOMCAT_HOME | sed -n '1p' | awk '{print $war_file}'`
	  if [[ $tomcat_pid = $tomcat_pid2 ]]
	  then
	    echo "tomcat is still alive, pid: $tomcat_pid"
	    echo "kill tomcat process"
	    kill -9 $tomcat_pid
	  fi
	fi
}

deployandstart() {
	cd $TOMCAT_HOME
	echo "backup old war"
	if [[ -f $WEBAPPS/$war_file ]]
	then
	  cd $BACKUP
	  today=`date +"%Y%m%d"`
	  if [[ ! -d $today ]]
	  then
	    mkdir $today
	  fi
	  cd $today
	  if [[ ! -f $war_file ]]
	  then
	    cp $WEBAPPS/$war_file .
	  fi
	fi

	echo delete old war
	rm -f $WEBAPPS/$war_file
	if [[ $deploy_mode = "-r" ]]
	then 
	  rm -rf $APPBASE/ROOT
	else
	  rm -rf $APPBASE/${war_file%.war}*
	fi

	echo copy new war
	cp $REPOSITORY/$war_file $WEBAPPS/
	if [[ $deploy_mode = "-r" ]]
	then
	  unzip -q $WEBAPPS/$war_file -d $APPBASE/ROOT
	else
	  cp $WEBAPPS/$war_file $APPBASE
	fi

	echo "start tomcat"
	bin/startup.sh
}

case "$cmd" in
	stop)
		stop
		;;
	deployandstart)
		deployandstart
		;;
	*)
		echo $"Usage: $0 {stop|deployandstart}"
		exit 1
esac
