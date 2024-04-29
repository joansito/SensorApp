FROM openjdk:17-jdk-slim
LABEL authors="joanm"

WORKDIR /app

COPY ./target/sensorconsumer-0.0.1-SNAPSHOT.jar /app/sensorconsumer.jar

ENTRYPOINT ["java","-jar","sensorconsumer.jar"]