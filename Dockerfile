FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_FILE=./build/libs/order_service-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS="-XX:+UseG1GC -Xmx1024m"
ENV TZ=Europe/Warsaw
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY ${JAR_FILE} /
COPY ./entrypoint.sh entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]