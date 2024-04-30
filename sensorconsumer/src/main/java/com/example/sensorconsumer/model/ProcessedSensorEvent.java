package com.example.sensorconsumer.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "processed_sensor_events")
public class ProcessedSensorEvent {

    @Id
    private String id;
    private String sensorId;
    private Instant timestamp;
    private String type;
    private double value;

}
