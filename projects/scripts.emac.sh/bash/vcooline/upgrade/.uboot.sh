#!/bin/bash

export REPOSITORY=/home/$USER/repository
export JAVA_HOME=/opt/java/jdk1.7.0_55
export JAVA_OPTS="-Xss256k"
export BOOT_HOME=/dyne/boots
export PATH=$JAVA_HOME/bin:$PATH

cmd=$1
app_file=$2
app_name=${app_file%.war}
app_name=${app_name%.jar}
profile=$3

stop() {
	cd $BOOT_HOME
	echo "check if app is up"
	app_pid=`jps -l | grep ${app_name} | awk '{print $1}'`
	if [[ $app_pid ]]
	then
	  kill -9 $app_pid
	fi
}

deployandstart() {
	cd $BOOT_HOME
	echo "delete old app"
	if [[ ! -d $app_name ]]
	then
	  mkdir $app_name
	else
	  if [[ -f $app_name/$app_file ]]
	  then
	    rm $app_name/$app_file
	  fi
	fi

	cd $app_name
	echo "copy new app"
	cp $REPOSITORY/$app_file .

	echo "unzip app"
	if [[ -d $BOOT_HOME/static/$app_name ]]
	then
	  rm -rf $BOOT_HOME/static/$app_name
	fi
	unzip -q $app_file -d $BOOT_HOME/static/$app_name

	if [[ $profile ]]
	then
	  JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$profile"
	fi

	echo "start app"
	$JAVA_HOME/jre/bin/java -jar $JAVA_OPTS $app_file & >/dev/null 2>&1
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
