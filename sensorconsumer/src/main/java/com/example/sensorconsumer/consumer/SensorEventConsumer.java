package com.example.sensorconsumer.consumer;

import com.example.sensorconsumer.model.ProcessedSensorEvent;
import com.example.sensorconsumer.repository.ProcessedSensorEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SensorEventConsumer {

    private final ProcessedSensorEventRepository repository;
    private final ObjectMapper objectMapper;

    public SensorEventConsumer(ProcessedSensorEventRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "sensor_events", groupId = "sensor_events_group")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received a sensor event: {}", record.value());
        try {
            ProcessedSensorEvent event = objectMapper.readValue(record.value(), ProcessedSensorEvent.class);
            repository.save(event);
            log.info("Processed and saved sensor event with ID: {}", event.getId());
        } catch (IOException e) {
            log.error("Failed to process sensor event", e);}
    }
}
