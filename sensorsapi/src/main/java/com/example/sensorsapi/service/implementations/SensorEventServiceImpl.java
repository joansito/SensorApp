package com.example.sensorsapi.service.implementations;

import com.example.sensorsapi.dto.SensorEventDTO;
import com.example.sensorsapi.mapper.SensorEventMapper;
import com.example.sensorsapi.model.SensorEvent;
import com.example.sensorsapi.repository.SensorEventRepository;
import com.example.sensorsapi.service.SensorEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorEventServiceImpl implements SensorEventService {

    @Autowired
    private SensorEventRepository sensorEventRepository;

    @Override
    public SensorEvent saveSensorEvent(SensorEventDTO sensorEventDTO) {
        try {
            SensorEvent sensorEvent = SensorEventMapper.toEntity(sensorEventDTO);
            return sensorEventRepository.save(sensorEvent);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to save sensor event", ex);
        }
    }

    @Override
    public List<SensorEvent> getAllSensorEvents() {
        return sensorEventRepository.findAll();
    }
}