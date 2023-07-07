package com.tsieducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stations", schema = "mydb")
@NamedQuery(name = "Stations.findAll", query = "SELECT s FROM StationsEntity s")
public class StationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "station_id")
    private int stationId;
    @Basic
    @Column(name = "station_name")
    private String stationName;

    @JsonIgnore
    @OneToMany(mappedBy = "departureByStationId", cascade = CascadeType.MERGE)
    private Collection<SchedulesEntity> departuresScheduleById;

    @JsonIgnore
    @OneToMany(mappedBy = "arrivalByStationId", cascade = CascadeType.MERGE)
    private Collection<SchedulesEntity> arrivalScheduleById;

}
