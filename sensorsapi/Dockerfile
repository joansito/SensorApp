FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /build
LABEL authors="joanm"
COPY sensorsapi/pom.xml /build/pom.xml
COPY sensorsapi/src /build/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /build/target/sensorsapi-0.0.1-SNAPSHOT.jar /app/sensorsapi.jar

ENTRYPOINT ["java", "-jar", "sensorsapi.jar"]

EXPOSE 8090
