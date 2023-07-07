package com.tsieducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "schedules", schema = "mydb")
@NamedQuery(name = "Schedules.findAll", query = "SELECT s FROM SchedulesEntity s")
@NamedQuery(name = "Schedules.findByDepartArrivalAndTime",
        query = "SELECT s FROM SchedulesEntity s "
        + "WHERE s.departureByStationId = :departureByStationId "
        + "AND s.arrivalByStationId = :arrivalByStationId "
        + "AND s.departureTime BETWEEN :startDate AND :endDate")
@NamedQuery(name = "Schedules.findByDepartStation",
        query = "SELECT s FROM SchedulesEntity s "
                + "WHERE s.departureByStationId = :departureByStationId")
@NamedQuery(name = "Schedules.findByDepartAndArrival",
        query = "SELECT s FROM SchedulesEntity s "
                + "WHERE s.departureByStationId = :departureByStationId "
                + "AND s.arrivalByStationId = :arrivalByStationId")
public class SchedulesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "schedule_id")
    private int scheduleId;
    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "train_id", nullable = false)
    private TrainsEntity trainByTrainId;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", referencedColumnName = "station_id", nullable = false)
    private StationsEntity departureByStationId;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", referencedColumnName = "station_id", nullable = false)
    private StationsEntity arrivalByStationId;

    @Column(name = "departure_time")
    private Date departureTime;

    @JsonIgnore
    @OneToMany(mappedBy = "scheduleByScheduleId", cascade = CascadeType.MERGE)
    private Collection<TicketsEntity> ticketsById;

}
