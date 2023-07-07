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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "trains", schema = "mydb")
@NamedQuery(name = "Trains.findAll", query = "SELECT s FROM TrainsEntity s")
public class TrainsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "train_id")
    private int trainId;
    @Basic
    @Column(name = "seats_quantity")
    private int seatsQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "trainByTrainId", cascade = CascadeType.MERGE)
    private Collection<SchedulesEntity> schedulesById;

}
