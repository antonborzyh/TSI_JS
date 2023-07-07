package com.tsieducation.repositories;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.StationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ScheduleRepository extends JpaRepository<SchedulesEntity, Integer> {

    List<SchedulesEntity> findByDepartureByStationId(StationsEntity departure);

    List<SchedulesEntity> findByDepartureByStationIdAndArrivalByStationId(
            StationsEntity departure, StationsEntity destination);

    @Query("SELECT s FROM SchedulesEntity s "
            + "WHERE s.departureByStationId = :departureByStationId "
            + "AND s.arrivalByStationId = :arrivalByStationId "
            + "AND s.departureTime BETWEEN :startDate AND :endDate")
    List<SchedulesEntity> retrieveByDepartureByStationIdAndArrivalByStationIdAndDepartureTime(
            @Param("departureByStationId") StationsEntity departStation,
            @Param("arrivalByStationId") StationsEntity destinationStation,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

}
