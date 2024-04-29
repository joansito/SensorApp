
FROM maven:3.8.4-openjdk-17 as build
LABEL authors="joanm"
WORKDIR /app

COPY pom.xml /app/
COPY sensorconsumer/pom.xml /app/sensorconsumer/pom.xml
COPY sensorconsumer/src /app/sensorconsumer/src

RUN mvn -f /app/sensorconsumer/pom.xml clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /app/sensorconsumer/target/sensorconsumer-0.0.1-SNAPSHOT.jar /app/sensorconsumer.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "sensorconsumer.jar"]

EXPOSE 8081