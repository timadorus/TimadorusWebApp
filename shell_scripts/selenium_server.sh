#!/bin/sh
XVFB_DISPLAY=":1"
XVFB_EXEC=`which Xvfb`

JAVA_EXEC=`which java`

HUB_PID_FILE="./hub.pid"
NODE_PID_FILE="./node.pid"

SELENIUM_LOG="./selenium.log"

SELENIUM_JAR="selenium-server-standalone.jar"

start() {
	# Starting a X session
	#echo "Starting a X session"
	nohup startx -- $XVFB_EXEC $XVFB_DISPLAY -screen 0 1024x768x24 2>&1 >/dev/null &
	#echo "X session started"		

	# Sleeping for 10 seconds just to be sure, that X is running before setting the display
	sleep 10

	# set Display
	export DISPLAY=$XVFB_DISPLAY
	
	# Starting the Hub
	#echo "Starting Selenium Hub"
	nohup $JAVA_EXEC -jar $SELENIUM_JAR -role hub > $SELENIUM_LOG &
	#HUB_PID=$!
	#echo $HUB_PID > $HUB_PID_FILE
	#echo "Selenium Hub started with PID $HUB_PID"

	# Sleeping for 5 seconds, just to be sure that the Hub is running
	sleep 5

	# Starting the Node
	#echo "Starting Selenium Node"
	nohup $JAVA_EXEC -jar $SELENIUM_JAR -role node -hub http://localhost:4444/grid/register > $SELENIUM_LOG &
	#NODE_PID=$!
	#echo $NODE_PID > $NODE_PID_FILE
	#echo "Selenium Node started with PID $NODE_PID"
	
	# Sleeping for 5 seconds, just to be sure that the Node is running
	sleep 5
}

stop() {
	# Stopping Selenium Node
	echo "Stopping Selenium Node"
	start-stop-daemon --stop --pidfile $NODE_PID_FILE
	rm $NODE_PID_FILE
	echo "Selenium Node stopped"
	
	# Stopping Selenium Hub
	echo "Stopping Selenium Hub"
	start-stop-daemon --stop --pidfile $HUB_PID_FILE
	rm $HUB_PID_FILE
	echo "Selenium Hub stopped"
	
	# Stopping XVFB and X sessions
	echo "Stopping XVFB and X sessions"
	start-stop-daemon --stop --name Xvfb
	echo "XVFB and X sessions stopped"
}

case "$1" in 
	"start")
		#start 
		
		nohup startx -- $XVFB_EXEC $XVFB_DISPLAY -screen 0 1024x768x24 2>&1 >/dev/null &
		sleep 10

		export DISPLAY=$XVFB_DISPLAY
	
		nohup $JAVA_EXEC -jar $SELENIUM_JAR -role hub > $SELENIUM_LOG &
	
		sleep 5

		nohup $JAVA_EXEC -jar $SELENIUM_JAR -role node -hub http://localhost:4444/grid/register > $SELENIUM_LOG &
		
		sleep 5

		;;

	"stop")
		start-stop-daemon --stop --name java
		start-stop-daemon --stop --name Xvfb
	
		;;	

	*)
		echo "Usage:"
		echo "$0 start for starting all necessary processes"
		echo "$0 stop for stopping all necessary processes"
		exit 1

		;;
esac

exit 0