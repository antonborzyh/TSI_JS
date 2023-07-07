package com.tsieducation.services.impl;

import com.tsieducation.entities.TrainsEntity;
import com.tsieducation.repositories.TrainRepository;
import com.tsieducation.services.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TrainServiceImpl implements TrainService {

    private TrainRepository trainRepository;

    @Override
    public TrainsEntity createTrain(TrainsEntity trainsEntity) {
        return trainRepository.save(trainsEntity);
    }

    @Override
    public List<TrainsEntity> getAllTrains() {
        return trainRepository.findAll();
    }
}
