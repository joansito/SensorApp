package com.example.sensorconsumer.repository;

import com.example.sensorconsumer.model.ProcessedSensorEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedSensorEventRepository extends MongoRepository<ProcessedSensorEvent, String> {
    // Here you can define any custom query methods you might need.
}
