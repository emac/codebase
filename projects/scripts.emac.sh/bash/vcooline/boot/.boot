#!/bin/bash

export JAVA_HOME=/dyne/javadev/jdk1.7.0_55
export JAVA_OPTS="-Xss256k"
export BOOT_HOME=/dyne/javadev/boots

app_name=${2%.war}
app_name=${app_name%.jar}

cd $BOOT_HOME
echo check if app is up
#app_pid=`ps fax | grep $2 | sed -n '5p' | awk '{print $1}'`
app_pid=`jps -l | grep $2 | awk '{print $1}'`
if [[ $app_pid ]]
then
  kill -9 $app_pid
fi

echo delete old war
if [[ ! -d $app_name ]]
then
  mkdir $app_name
else
  if [[ -f $app_name/$2 ]]
  then
    rm $app_name/$2
  fi
fi

echo copy new war
cp $1/$2 .

echo unzip war
if [[ -d $BOOT_HOME/static/$app_name ]]
then
  rm -rf $BOOT_HOME/static/$app_name
fi
unzip -q $2 -d $BOOT_HOME/static/$app_name

if [[ $3 ]]
then
  JAVA_OPTS="$JAVA_OPTS -Dspring.profiles.active=$3"
fi

echo start app
$JAVA_HOME/jre/bin/java -jar $JAVA_OPTS $2 & >/dev/null 2>&1
