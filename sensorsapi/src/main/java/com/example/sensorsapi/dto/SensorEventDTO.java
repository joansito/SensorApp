package com.example.sensorsapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.Instant;

@Data
public class SensorEventDTO {

    @NotNull(message = "Sensor ID cannot be null")
    private String sensorId;

    @NotNull(message = "Timestamp cannot be null")
    @PastOrPresent(message = "Timestamp must be in the past or present")
    private Instant timestamp;

    @NotNull(message = "Type cannot be null")
    private String type;

    private double value;


}
