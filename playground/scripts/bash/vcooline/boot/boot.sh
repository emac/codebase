#!/bin/bash

export USER_NAME=emac
export REPOSITORY=/home/$USER_NAME/repository
export LOG_FILE=/home/$USER_NAME/logs/`date +"%F"`.log

if [[ -f $REPOSITORY/$1 ]]
then
  cd /home/$USER_NAME/scripts
  sudo sh .boot $REPOSITORY $1 $2 >>$LOG_FILE 2>&1
fi
