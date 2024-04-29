package com.example.sensorsapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@Document(collection = "sensor_events")
public class SensorEvent {

    @Id
    private String id;

    @Indexed
    private String sensorId;

    @Indexed
    private Instant timestamp;

    private String type;

    private double value;
}
