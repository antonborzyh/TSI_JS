package com.tsieducation.controllers.api;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.StationsEntity;
import com.tsieducation.services.ScheduleService;
import com.tsieducation.services.StationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/schedules")
public class ScheduleController {

    private StationService stationService;
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getSchedulesByDepartDestinationAndTime(
            @RequestParam("departureStationName") String departureStationName,
            @RequestParam("destinationStationName") String destinationStationName,
            @RequestParam("startDate") String startDateString,
            @RequestParam("endDate") String endDateString) {
        StationsEntity departStation = stationService.getStationByName(departureStationName);
        StationsEntity destinationStation = stationService.getStationByName(destinationStationName);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);

            List<SchedulesEntity> schedulesEntities = scheduleService.getScheduleByTimeAndStations(
                    departStation, destinationStation, startDate, endDate);

            Map<String, List<Map<String, Object>>> schedules = new HashMap<>();
            schedules.put("schedules", new ArrayList<>());

            for (SchedulesEntity schedule : schedulesEntities) {
                Map<String, Object> scheduleMap = new HashMap<>();
                scheduleMap.put("scheduleId", schedule.getScheduleId());
                scheduleMap.put("trainId", schedule.getTrainByTrainId().getTrainId());
                scheduleMap.put("departStation", schedule.getDepartureByStationId().getStationName());
                scheduleMap.put("destinationStation", schedule.getArrivalByStationId().getStationName());
                scheduleMap.put("departureTime", schedule.getDepartureTime().toString());
                schedules.get("schedules").add(scheduleMap);
            }

            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (ParseException e) {
            return (ResponseEntity<Map<String, List<Map<String, Object>>>>) ErrorResponse.create(e, HttpStatus.BAD_REQUEST, "Incorrect date format");
        }
    }

    @GetMapping
    @RequestMapping("station")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getScheduleForTheStation(
            @RequestParam("departureStationName") String departureStationName) {
        StationsEntity departStation = stationService.getStationByName(departureStationName);

        List<SchedulesEntity> schedulesEntities = scheduleService.getSchedulesByDepart(departStation);

        Map<String, List<Map<String, Object>>> schedules = new HashMap<>();
        schedules.put("schedules", new ArrayList<>());

        for (SchedulesEntity schedule : schedulesEntities) {
            Map<String, Object> scheduleMap = new HashMap<>();
            scheduleMap.put("scheduleId", schedule.getScheduleId());
            scheduleMap.put("trainId", schedule.getTrainByTrainId().getTrainId());
            scheduleMap.put("departStation", schedule.getDepartureByStationId().getStationName());
            scheduleMap.put("destinationStation", schedule.getArrivalByStationId().getStationName());
            scheduleMap.put("departureTime", schedule.getDepartureTime().toString());
            schedules.get("schedules").add(scheduleMap);
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}
