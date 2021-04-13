#!/usr/bin/env bash
#required for proper logging of source in graylog
export SERVER_HOSTNAME=$HOSTNAME
if [ ! -z "$CREDENTIALS_FILE_NAME" ] && [ -r "/run/secrets/$CREDENTIALS_FILE_NAME" ]; then
	. /run/secrets/$CREDENTIALS_FILE_NAME
fi
java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS  -jar order_service-0.0.1-SNAPSHOT.jar