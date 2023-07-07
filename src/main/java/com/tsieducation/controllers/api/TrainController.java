package com.tsieducation.controllers.api;

import com.tsieducation.entities.TrainsEntity;
import com.tsieducation.services.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/trains")
public class TrainController {
    
    private TrainService trainService;

    // Build CREATE TrainsEntity REST API
    @PostMapping
    public ResponseEntity<TrainsEntity> createTrain(@RequestBody TrainsEntity trainsEntity) {
        TrainsEntity savedTrainsEntity = trainService.createTrain(trainsEntity);
        return new ResponseEntity<>(savedTrainsEntity, HttpStatus.CREATED);
    }

    // Build GET All TrainsEntity REST API
    // http://localhost:8079/api/trains
    @GetMapping
    public ResponseEntity<Map<String, List<TrainsEntity>>> getAllTrains() {
        List<TrainsEntity> trainsEntities = trainService.getAllTrains();
        return new ResponseEntity<>(Map.of("trains", trainsEntities), HttpStatus.OK);
    }
}
