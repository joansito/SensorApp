# Use maven:3.8.4-openjdk-17 as a base image for the build stage
FROM maven:3.8.4-openjdk-17 as build

# Set the author of the Dockerfile
LABEL authors="joanm"

# Set the working directory to /app
WORKDIR /app

# Copy the project's root pom.xml to /app/
COPY pom.xml /app/

# Copy the sensorconsumer module's pom.xml to /app/sensorconsumer/
COPY sensorconsumer/pom.xml /app/sensorconsumer/pom.xml

# Copy the sensorconsumer module's source code to /app/sensorconsumer/src
COPY sensorconsumer/src /app/sensorconsumer/src

# Run mvn clean package command on the sensorconsumer module, skipping tests
RUN mvn -f /app/sensorconsumer/pom.xml clean package -DskipTests

# Start a new stage with openjdk:17-jdk-slim as the base image
FROM openjdk:17-jdk-slim

# Copy the built jar file from the build stage to /app/sensorconsumer.jar
COPY --from=build /app/sensorconsumer/target/sensorconsumer-0.0.1-SNAPSHOT.jar /app/sensorconsumer.jar

# Set the working directory to /app
WORKDIR /app

# Set the command to run when the container starts
ENTRYPOINT ["java", "-jar", "sensorconsumer.jar"]

# Expose port 8081 for the application to listen on
EXPOSE 8081
```