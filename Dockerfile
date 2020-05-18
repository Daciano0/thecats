FROM openjdk:11-jdk-slim
VOLUME /temp
ADD build/libs/*.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]