#!/bin/bash

export LOG_FILE=/home/$USER/logs/`date +"%F"`.log
export SCRIPTS=/home/$USER/scripts
export REPOSITORY=/home/$USER/repository

export SSH_USER=$USER
export SSH_HOST=
export SSH_PORT=

app=$1

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

sync $app 
stop $app
deployAndStart $app production
