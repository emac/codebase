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

war_file=$1
mysql_script=$2

if [[ ! -f $REPOSITORY/$war_file ]]
then
	echo "File does not exist: $REPOSITORY/$war_file"
	exit 1
fi

echo "copy war file to slaves"
scp -P $SSH_PORT $REPOSITORY/$war_file $SSH_USER@$SSH_HOST:$REPOSITORY

echo "stop tomcats in master/slaves"
sh $SCRIPTS/.utomcat.sh stop $war_file >>$LOG_FILE 2>&1
ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.utomcat.sh stop $war_file >>$LOG_FILE 2>&1"

if [[ $mysql_script ]]
then
	echo "execute SQL script to update database scheme"
	read -p "Enter MySQL password:" MYSQL_PASSWORD
	mysql -h$MYSQL_HOST -P$MYSQL_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD -D$MYSQL_DATABASE < $mysql_script
	read -p "Press Enter to continue..."
fi

echo "deploy new app and start tomcats in master/slaves"
sh $SCRIPTS/.utomcat.sh deployandstart $war_file -r >>$LOG_FILE 2>&1
ssh -p $SSH_PORT $SSH_USER@$SSH_HOST "sh $SCRIPTS/.utomcat.sh deployandstart $war_file -r >>$LOG_FILE 2>&1"
