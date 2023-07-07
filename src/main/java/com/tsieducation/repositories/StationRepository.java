package com.tsieducation.repositories;

import com.tsieducation.entities.StationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StationRepository extends JpaRepository<StationsEntity, Integer> {

    StationsEntity findByStationName(String name);

}
