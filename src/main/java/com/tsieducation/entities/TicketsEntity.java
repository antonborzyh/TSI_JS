package com.tsieducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tickets", schema = "mydb")
@NamedQuery(name = "Tickets.findAll", query = "SELECT s FROM TicketsEntity s")
@NamedQuery(name = "Tickets.findBySchedule", query = "SELECT s FROM TicketsEntity s "
        + "WHERE s.scheduleByScheduleId = :scheduleByScheduleId")
public class TicketsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ticket_id")
    private int ticketId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id", nullable = false)
    private SchedulesEntity scheduleByScheduleId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UsersEntity userByUserId;

}
