FROM openjdk:17-jdk-slim
COPY --from=build /app/target/sensorconsumer-0.0.1-SNAPSHOT.jar /app/sensorconsumer.jar
ENTRYPOINT ["java", "-jar", "/app/sensorconsumer.jar"]

FROM maven:3.8.4-openjdk-17 as build
LABEL authors="joanm"

COPY sensorconsumer/pom.xml /build/pom.xml
COPY sensorconsumer/src /build/src

WORKDIR /build


RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim


COPY --from=build /build/target/sensorconsumer-0.0.1-SNAPSHOT.jar /app/sensorconsumer.jar

WORKDIR /app


ENTRYPOINT ["java", "-jar", "sensorconsumer.jar"]

EXPOSE 8081