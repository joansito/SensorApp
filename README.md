# SensorApp

SensorApp is composed of microservices designed to handle sensor event data, leveraging Spring Boot, Kafka, and MongoDB technologies.

## Project Architecture

The application is split into two primary services:

- **sensorsapi**: This microservice provides RESTful APIs to capture and manage sensor event data.
- **sensorconsumer**: This service consumes sensor event data from a Kafka topic, processes it, and persists it to MongoDB.

Both microservices are intended to work in tandem to efficiently handle the lifecycle of sensor event data.

## Setup and Running

### Prerequisites

- Docker and Docker Compose installed
- Java 17 installed (if running locally outside Docker)
- Maven installed (if compiling the JAR files manually)

### Running with Docker Compose

To get the application up and running with Docker:

1. Clone the repository:
   ```sh
   git clone https://github.com/joansito/SensorApp
   ```
2. Change to the project directory:
   ```sh
   cd SensorApp
   ```
3. Start all services with Docker Compose:
   ```sh
   docker-compose up --build
   ```

The above commands will start the services defined in `docker-compose.yml`, including MongoDB and Kafka.

### MongoDB Configuration

MongoDB is started as a service in `docker-compose.yml`. Any specific configurations can be applied within the `mongo` service definition under the `environment` key.

### Kafka Configuration

Configuration settings for Kafka are specified in the `docker-compose.yml` file under the `kafka` service. Update the environment variables as needed to match your Kafka setup.

### Mongo Express

Mongo Express is configured to provide a web-based MongoDB admin interface to easily manage your MongoDB data. It can be accessed at:

    URL: http://localhost:8081
    Username: admin
    Password: pass

## API Documentation

### sensorsapi Endpoints

- **POST /api/sensor-events**: Endpoint to submit new sensor event data.
    - **Example Payload**:
      ```json
      {
        "sensorId": "123",
        "timestamp": "2024-02-15T12:34:56Z",
        "type": "temperature",
        "value": 25.3
      }
      ```
- **GET /api/sensor-events**: Endpoint to retrieve all sensor event data.

### sensorconsumer

The `sensorconsumer` service automatically consumes messages from a Kafka topic named `sensor_events` and does not expose any RESTful APIs.

#### This project was created with care by Joansito.