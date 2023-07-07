package com.tsieducation.controllers.api;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.StationsEntity;
import com.tsieducation.entities.TicketsEntity;
import com.tsieducation.services.ScheduleService;
import com.tsieducation.services.StationService;
import com.tsieducation.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("api/tickets")
public class TicketController {

    private ScheduleService scheduleService;
    private StationService stationService;
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getPassengersForTheSchedule(
            @RequestParam("departureStationName") String departureStationName,
            @RequestParam("destinationStationName") String destinationStationName) {
        StationsEntity departStation = stationService.getStationByName(departureStationName);
        StationsEntity destinationStation = stationService.getStationByName(destinationStationName);

        List<SchedulesEntity> schedulesEntities = scheduleService.getScheduleByDepartAndDestination(
                departStation, destinationStation);

        List<TicketsEntity> tickets = new ArrayList<>();

        for (SchedulesEntity schedule : schedulesEntities) {
            tickets.addAll(ticketService.getTicketsBySchedule(schedule));
        }

        Map<String, List<Map<String, Object>>> passengers = new HashMap<>();
        passengers.put("passengers", new ArrayList<>());

        for (TicketsEntity ticket : tickets) {
            Map<String, Object> passenger = new HashMap<>();
            passenger.put("ticketId", ticket.getTicketId());
            passenger.put("passengerFirstName", ticket.getUserByUserId().getFirstName());
            passenger.put("passengerLastName", ticket.getUserByUserId().getLastName());
            passenger.put("departStation", ticket.getScheduleByScheduleId().getDepartureByStationId().getStationName());
            passenger.put("destinationStation", ticket.getScheduleByScheduleId().getArrivalByStationId().getStationName());
            passenger.put("departureTime", ticket.getScheduleByScheduleId().getDepartureTime());
            passengers.get("passengers").add(passenger);
        }

        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }
}
