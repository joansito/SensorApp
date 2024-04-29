FROM maven:3.8.4-openjdk-17 as build
LABEL authors="joanm"

WORKDIR /app

COPY pom.xml /app/
COPY sensorsapi/pom.xml /app/sensorsapi/pom.xml
COPY sensorsapi/src /app/sensorsapi/src

RUN mvn -f /app/sensorsapi/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/sensorsapi/target/sensorsapi-0.0.1-SNAPSHOT.jar /app/sensorsapi.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "sensorsapi.jar"]

EXPOSE 8090
