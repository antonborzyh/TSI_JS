package com.tsieducation.services.impl;

import com.tsieducation.entities.StationsEntity;
import com.tsieducation.repositories.StationRepository;
import com.tsieducation.services.StationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StationServiceImpl implements StationService {

    private StationRepository stationRepository;

    @Override
    public StationsEntity createStation(StationsEntity stationsEntity) {
        return stationRepository.save(stationsEntity);
    }

    @Override
    public List<StationsEntity> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public StationsEntity getStationByName(String stationName) {
        return stationRepository.findByStationName(stationName);
    }
}
