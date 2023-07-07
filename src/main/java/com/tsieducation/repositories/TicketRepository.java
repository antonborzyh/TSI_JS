package com.tsieducation.repositories;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.TicketsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<TicketsEntity, Integer> {

    List<TicketsEntity> findByScheduleByScheduleId(SchedulesEntity schedulesEntity);

}
