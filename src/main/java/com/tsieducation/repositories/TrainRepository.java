package com.tsieducation.repositories;

import com.tsieducation.entities.TrainsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainRepository extends JpaRepository<TrainsEntity, Integer> {

}
