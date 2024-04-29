package com.example.sensorsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorEventRepository extends MongoRepository<com.example.sensorsapi.model.SensorEvent, String>{
}
