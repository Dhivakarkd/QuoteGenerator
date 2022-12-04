FROM openjdk:8-jdk-alpine
USER 1000:1000
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar  app.jar
ENTRYPOINT java $JAVA_OPTS -jar /app.jar