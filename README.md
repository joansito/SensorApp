# SensorApp

SensorApp is composed of microservices designed to handle sensor event data, leveraging Spring Boot, Kafka, and MongoDB technologies.

## Project Architecture

The application is split into two primary services:

- **sensorsapi**: This microservice provides RESTful APIs to capture and manage sensor event data.
- **sensorconsumer**: This service consumes sensor event data from a Kafka topic, processes it, and persists it to MongoDB.

Both microservices are intended to work in tandem to efficiently handle the lifecycle of sensor event data.

## Database Design and Data Flow

The `SensorApp` utilizes MongoDB to store and process sensor event data through two primary collections within the `sensor_db` database:

- **sensor_events**: This collection is used by the `sensorsapi` service to store sensor event data as it is received. Each POST request to `/api/sensor-events` results in data being saved here, ensuring all raw sensor event data is captured.

- **processed_sensor_events**: After sensor events are stored, the `sensorconsumer` service consumes these events from a Kafka topic, processes them, and then saves the processed data into this collection. 

This setup ensures a clear separation of concerns where raw data collection and data processing are handled independently, promoting scalability and maintainability of the system.


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

## Pending Tasks and Improvements

The `SensorApp` is currently functional but has several areas identified for improvement to enhance functionality, security, and maintainability. The following tasks are planned:

### Testing
- **Create Code Testing in General**: Develop comprehensive unit and integration tests for both microservices to ensure reliability and facilitate continuous integration and deployment processes.

### Security Enhancements
- **Implementation of Logins and Security Measures**: Implement authentication and authorization mechanisms to secure API endpoints, possibly using OAuth2 or JWT for securing REST APIs.

### Code Refactoring
- **Decoupling of Functions in the Consumer Class**: Refactor the `sensorconsumer` service to separate concerns more effectively. This includes creating a dedicated service layer with an implementation (`impl`) to handle business logic, thereby making the code cleaner and more maintainable.

### Exception Handling
- **Create Custom Exceptions in the Consumer**: Enhance error handling in the `sensorconsumer` service by defining custom exceptions that can provide more detailed context about errors, aiding in debugging and user feedback.

### Reactive Programming
- **Apply a Reactive Approach Using WebFlux**: Transition the services to a reactive architecture to handle more concurrent requests efficiently and improve the scalability of the application. This involves integrating Spring WebFlux to manage asynchronous stream-based data processing.

### Data Integrity
- **Prevent Duplicate Records**: Implement logic to prevent the creation of records in the `sensor_events` collection that have the same `sensorId` and `timestamp`, even if they possess different IDs. This would ensure data uniqueness and integrity.

Contributions and suggestions on these tasks are welcome and appreciated.

#### Designed with love by Joansito.

