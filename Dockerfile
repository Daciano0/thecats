FROM amazonlinux2-correttojdk11

VOLUME /tmp

ENV APP_NAME ame-integrator.jar

COPY ["thecat.jar", "newrelic.yml", "/application/"]

RUN bash -c 'touch /application/$APP_NAME'

WORKDIR  /application

EXPOSE 8080

# Start tomcat
ENTRYPOINT exec java -javaagent:/application/newrelic.jar $JAVA_OPTS $EXTRA_PROPERTIES -jar /application/$APP_NAME
