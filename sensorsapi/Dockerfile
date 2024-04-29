FROM openjdk:17-jdk-slim

LABEL authors="joanm"

WORKDIR /app

COPY ./target/sensorsapi-0.0.1-SNAPSHOT.jar /app/sensorsapi.jar

ENTRYPOINT ["java","-jar","/app/sensorsapi.jar"]