#!/bin/bash

export LOG_FILE=/home/$USER/logs/`date +"%F"`.log
export SCRIPTS=/home/$USER/scripts
export REPOSITORY=/home/$USER/repository

export SSH_USER=$USER
export SSH_HOST=
export SSH_PORT=

export MYSQL_HOST=
export MYSQL_PORT=
export MYSQL_DATABASE=
export MYSQL_USER=

app_file=$1
profile=$2
mysql_script=$3

if [[ ! -f $REPOSITORY/$app_file ]]
then
	echo "File does not exist: $REPOSITORY/$app_file"
	exit 1
fi

echo "copy app file to slaves"
scp -P $SSH_PORT $REPOSITORY/$app_file $SSH_USER@$SSH_HOST:$REPOSITORY

echo "stop apps in master/slaves"
sh $SCRIPTS/.uboot.sh stop >>$LOG_FILE 2>&1
ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.uboot.sh stop >>$LOG_FILE 2>&1"

if [[ $mysql_script ]]
then
	echo "execute SQL script to update database scheme"
	read -p "Enter MySQL password:" MYSQL_PASSWORD
	mysql -h$MYSQL_HOST -P$MYSQL_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD -D$MYSQL_DATABASE < $mysql_script
	read -p "Press Enter to continue..."
fi

echo "deploy new app and start apps in master/slaves"
sh $SCRIPTS/.uboot.sh deployandstart $app_file $profile >>$LOG_FILE 2>&1
ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.uboot.sh deployandstart $app_file $profile >>$LOG_FILE 2>&1"
