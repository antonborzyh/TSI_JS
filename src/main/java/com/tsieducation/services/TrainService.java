package com.tsieducation.services;

import com.tsieducation.entities.TrainsEntity;

import java.util.List;


public interface TrainService {

    TrainsEntity createTrain(TrainsEntity trainsEntity);

    List<TrainsEntity> getAllTrains();

}
