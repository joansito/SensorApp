# Use maven:3.8.4-openjdk-17 as a base image for the build stage
FROM maven:3.8.4-openjdk-17 as build

# Set the author of the Dockerfile
LABEL authors="joanm"

# Set the working directory to /app
WORKDIR /app

# Copy the project's root pom.xml to /app/
COPY pom.xml /app/

# Copy the sensorsapi module's pom.xml to /app/sensorsapi/
COPY sensorsapi/pom.xml /app/sensorsapi/pom.xml

# Copy the sensorsapi module's source code to /app/sensorsapi/src
COPY sensorsapi/src /app/sensorsapi/src

# Run mvn clean package command on the sensorsapi module, skipping tests
RUN mvn -f /app/sensorsapi/pom.xml clean package -DskipTests

# Start a new stage with openjdk:17-jdk-slim as the base image
FROM openjdk:17-jdk-slim

# Copy the built jar file from the build stage to /app/sensorsapi.jar
COPY --from=build /app/sensorsapi/target/sensorsapi-0.0.1-SNAPSHOT.jar /app/sensorsapi.jar

# Set the working directory to /app
WORKDIR /app

# Set the command to run when the container starts
ENTRYPOINT ["java", "-jar", "sensorsapi.jar"]

# Expose port 8090 for the application to listen on
EXPOSE 8090