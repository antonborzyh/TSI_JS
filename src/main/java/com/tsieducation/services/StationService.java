package com.tsieducation.services;

import com.tsieducation.entities.StationsEntity;

import java.util.List;


public interface StationService {
    StationsEntity createStation(StationsEntity stationsEntity);

    StationsEntity getStationByName(String stationName);

    List<StationsEntity> getAllStations();
}
