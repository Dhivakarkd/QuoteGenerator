FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar  app.jar
RUN mkdir -p /var/quotes/
ENTRYPOINT java $JAVA_OPTS -jar /app.jar