FROM java:8u111-jre-alpine

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV APPLICATION_NAME=app
ENV EXECUTE_JAR=${APPLICATION_NAME}-server.jar

ENV PORT=8080 \
    JVM_XMX=512m


COPY . /data

VOLUME /data/logs

WORKDIR /data

CMD exec java -Dfile.encoding=UTF-8 -Xmx${JVM_XMX} -Xms128m -Xss256k \
-jar ${EXECUTE_JAR} \
--PORT=${PORT} \
--APPLICATION_NAME=${APPLICATION_NAME} \
--CONFIG_URI=${CONFIG_URI} \
--CONFIG_USERNAME=${CONFIG_USERNAME} \
--CONFIG_PASSWORD=${CONFIG_PASSWORD} \
--CONFIG_LABEL=${CONFIG_LABEL} \
--CONFIG_PROFILE=${CONFIG_PROFILE}