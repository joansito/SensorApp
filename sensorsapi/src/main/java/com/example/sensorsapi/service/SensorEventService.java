package com.example.sensorsapi.service;

import com.example.sensorsapi.dto.SensorEventDTO;
import com.example.sensorsapi.model.SensorEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorEventService {
    SensorEvent saveSensorEvent(SensorEventDTO sensorEventDTO);
    List<SensorEvent> getAllSensorEvents();
}
