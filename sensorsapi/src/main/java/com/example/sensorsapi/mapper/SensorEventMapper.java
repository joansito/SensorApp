package com.example.sensorsapi.mapper;

import com.example.sensorsapi.dto.SensorEventDTO;
import com.example.sensorsapi.model.SensorEvent;

public class SensorEventMapper {
    public static SensorEvent toEntity(SensorEventDTO dto) {
        SensorEvent sensorEvent = new SensorEvent();
        sensorEvent.setSensorId(dto.getSensorId());
        sensorEvent.setTimestamp(dto.getTimestamp());
        sensorEvent.setType(dto.getType());
        sensorEvent.setValue(dto.getValue());
        return sensorEvent;
    }

    public static SensorEventDTO toDTO(SensorEvent sensorEvent) {
        SensorEventDTO dto = new SensorEventDTO();
        dto.setSensorId(sensorEvent.getSensorId());
        dto.setTimestamp(sensorEvent.getTimestamp());
        dto.setType(sensorEvent.getType());
        dto.setValue(sensorEvent.getValue());
        return dto;
    }
}
