package com.example.sensorsapi.controller;

import com.example.sensorsapi.dto.SensorEventDTO;
import com.example.sensorsapi.model.SensorEvent;
import com.example.sensorsapi.service.SensorEventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorEventController {
    private static final Logger log = LoggerFactory.getLogger(SensorEventController.class);

    private final SensorEventService sensorEventService;

    SensorEventController(SensorEventService sensorEventService) {
        this.sensorEventService = sensorEventService;
    }

    @PostMapping("/sensor-events")
    public ResponseEntity<SensorEvent> createSensorEvent(@Valid @RequestBody SensorEventDTO sensorEventDTO) {
        log.info("Received sensor event for sensorId: {}", sensorEventDTO.getSensorId());
        SensorEvent createdEvent = sensorEventService.saveSensorEvent(sensorEventDTO);
        log.info("Sensor event created with id: {}", createdEvent.getId());
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