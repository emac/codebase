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

mysql_script=$1

sync() {
	if [[ ! -f $REPOSITORY/$1 ]]
	then
		echo "File does not exist: $REPOSITORY/$1"
		exit 1
	fi

	echo "copy $1 to slaves"
	scp -P $SSH_PORT $REPOSITORY/$1 $SSH_USER@$SSH_HOST:$REPOSITORY
}

stop() {
	echo "stop $1 in master/slaves"
	sh $SCRIPTS/.uboot.sh stop $1 >>$LOG_FILE 2>&1
	ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.uboot.sh stop $1 >>$LOG_FILE 2>&1"
}

deployAndStart() {
	echo "deploy and start $1 in master/slaves"
	sh $SCRIPTS/.uboot.sh deployandstart $1 $2 >>$LOG_FILE 2>&1
	ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.uboot.sh deployandstart $1 $2 >>$LOG_FILE 2>&1"
}

sync fxt-wap.war
sync fxt-api.jar
sync fxt-admin.war
sync fxt-wap-verify.war

stop fxt-wap.war
stop fxt-api.jar
stop fxt-admin.war
stop fxt-wap-verify.war

if [[ $mysql_script ]]
then
	echo "execute SQL script to update database scheme"
	read -p "Enter MySQL password:" MYSQL_PASSWORD
	mysql -h$MYSQL_HOST -P$MYSQL_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD -D$MYSQL_DATABASE < $mysql_script
	read -p "Press Enter to continue..."
fi

deployAndStart fxt-wap.war production
deployAndStart fxt-api.jar production
deployAndStart fxt-admin.war production
deployAndStart fxt-wap-verify.war production
