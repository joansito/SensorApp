package com.example.sensorsapi.controller;

import com.example.sensorsapi.dto.SensorEventDTO;
import com.example.sensorsapi.model.SensorEvent;
import com.example.sensorsapi.service.SensorEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorEventController {
    private static final Logger log = LoggerFactory.getLogger(SensorEventController.class);
    private static final String SENSOR_EVENTS_TOPIC = "sensor_events"; // The Kafka topic to publish to

    private final SensorEventService sensorEventService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public SensorEventController(SensorEventService sensorEventService,
                                 KafkaTemplate<String, String> kafkaTemplate,
                                 ObjectMapper objectMapper) {
        this.sensorEventService = sensorEventService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/sensor-events")
    public ResponseEntity<SensorEvent> createSensorEvent(@Valid @RequestBody SensorEventDTO sensorEventDTO) {
        log.info("Received sensor event for sensorId: {}", sensorEventDTO.getSensorId());
        SensorEvent createdEvent = sensorEventService.saveSensorEvent(sensorEventDTO);
        log.info("Sensor event created with id: {}", createdEvent.getId());

        // Convert sensorEventDTO to JSON string for Kafka publishing
        try {
            String sensorEventJson = objectMapper.writeValueAsString(sensorEventDTO);
            kafkaTemplate.send(SENSOR_EVENTS_TOPIC, sensorEventJson);
            log.info("Published sensor event to Kafka topic: {}", SENSOR_EVENTS_TOPIC);
        } catch (JsonProcessingException e) {
            log.error("Error converting sensor event to JSON", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/sensor-events")
    public ResponseEntity<List<SensorEvent>> getAllSensorEvents() {
        log.info("Fetching all sensor events");
        List<SensorEvent> sensorEvents = sensorEventService.getAllSensorEvents();
        log.info("Fetched {} sensor events", sensorEvents.size());
        return new ResponseEntity<>(sensorEvents, HttpStatus.OK);
    }

}