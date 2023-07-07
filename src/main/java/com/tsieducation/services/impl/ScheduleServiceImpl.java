package com.tsieducation.services.impl;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.StationsEntity;
import com.tsieducation.repositories.ScheduleRepository;
import com.tsieducation.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Override
    public List<SchedulesEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<SchedulesEntity> getSchedulesByDepart(StationsEntity departStation) {
        return scheduleRepository.findByDepartureByStationId(departStation);
    }

    @Override
    public List<SchedulesEntity> getScheduleByDepartAndDestination(
            StationsEntity departStation, StationsEntity destinationStation) {
        return scheduleRepository.findByDepartureByStationIdAndArrivalByStationId(
                departStation, destinationStation);
    }

    @Override
    public List<SchedulesEntity> getScheduleByTimeAndStations(
            StationsEntity departStation, StationsEntity destinationStation, Date startDate, Date endDate) {
        return scheduleRepository.retrieveByDepartureByStationIdAndArrivalByStationIdAndDepartureTime(
                departStation, destinationStation, startDate, endDate);
    }
}
