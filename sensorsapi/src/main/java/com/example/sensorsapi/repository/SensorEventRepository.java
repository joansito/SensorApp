package com.example.sensorsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorEventRepository extends MongoRepository<com.example.sensorsapi.model.SensorEvent, String>{
}
