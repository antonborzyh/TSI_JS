package com.tsieducation.controllers.api;

import com.tsieducation.entities.StationsEntity;
import com.tsieducation.services.StationService;
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
@RequestMapping("api/stations")
public class StationController {

    private StationService stationService;

    // Build CREATE StationsEntity REST API
    @PostMapping
    public ResponseEntity<StationsEntity> createStation(@RequestBody StationsEntity stationsEntity) {
        StationsEntity savedStationsEntity = stationService.createStation(stationsEntity);
        return new ResponseEntity<>(savedStationsEntity, HttpStatus.CREATED);
    }

    // Build GET All StationsEntity REST API
    // http://localhost:8079/api/stations
    @GetMapping
    public ResponseEntity<Map<String, List<StationsEntity>>> getAllStations() {
        List<StationsEntity> stationsEntities = stationService.getAllStations();
        return new ResponseEntity<>(Map.of("stations", stationsEntities), HttpStatus.OK);
    }
}
