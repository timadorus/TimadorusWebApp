#!/bin/sh
XVFB_DISPLAY=":1"
XVFB_EXEC=`which Xvfb`

JAVA_EXEC=`which java`

SELENIUM_LOG="./selenium.log"

SELENIUM_JAR="selenium-server-standalone.jar"

start() {
	# Starting a X session
	echo "Starting a X session"
	nohup startx -- $XVFB_EXEC $XVFB_DISPLAY -screen 0 1024x768x24 >/dev/null &
	echo "X session started"		

	# Sleeping for 10 seconds just to be sure, that X is running before setting the display
	sleep 10

	# set Display
	export DISPLAY=$XVFB_DISPLAY
	
	# Starting the Hub
	echo "Starting Selenium Hub"
	$JAVA_EXEC -jar $SELENIUM_JAR -role hub > $SELENIUM_LOG &
	HUB_PID=$!
	echo "Selenium Hub started with PID $HUB_PID"

	# Sleeping for 5 seconds, just to be sure that the Hub is running
	sleep 5

	# Starting the Node
	echo "Starting Selenium Node"
	$JAVA_EXEC -jar $SELENIUM_JAR -role node -hub http://localhost:4444/grid/register > $SELENIUM_LOG &
	NODE_PID=$!
	echo "Selenium Node started with PID $NODE_PID"
	
	# Sleeping for 5 seconds, just to be sure that the Node is running
	sleep 5
}

stop() {
	# Stopping Selenium Node and Hub
	echo "Stopping Selenium Node and Hub"
	start-stop-daemon --stop --name java
	echo "Selenium Node and Hub stopped"
	
	# Stopping XVFB and X sessions
	echo "Stopping XVFB and X sessions"
	start-stop-daemon --stop --name Xvfb
	echo "XVFB and X sessions stopped"
}

case "$1" in 
	"start")
		start 

		;;

	"stop")
		stop
	
		;;	

	*)
		echo "Usage:"
		echo "$0 start for starting all necessary processes"
		echo "$0 stop for stopping all necessary processes"
		exit 1

		;;
esac

exit 0