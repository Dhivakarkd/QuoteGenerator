FROM gradle:jdk8-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jdk-alpine
USER 1000:1000
ARG JAR_FILE=target/*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar  app.jar
ENTRYPOINT java $JAVA_OPTS -jar /app.jar