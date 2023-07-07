package com.tsieducation.services;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.StationsEntity;

import java.util.Date;
import java.util.List;


public interface ScheduleService {

    List<SchedulesEntity> getAllSchedules();

    List<SchedulesEntity> getSchedulesByDepart(StationsEntity departStation);

    List<SchedulesEntity> getScheduleByDepartAndDestination(
            StationsEntity departStation, StationsEntity destinationStation);

    List<SchedulesEntity> getScheduleByTimeAndStations(
            StationsEntity departStation, StationsEntity destinationStation, Date startDate, Date endDate);
}
