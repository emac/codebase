#!/bin/bash

export REPOSITORY=/home/$USER/repository
export LOG_FILE=/home/$USER/logs/`date +"%F"`.log

if [[ -f $REPOSITORY/$1 ]]
then
  cd /home/$USER/scripts
  sh .deploy $REPOSITORY $1 tomcat localhost $2 >>$LOG_FILE 2>&1
fi
