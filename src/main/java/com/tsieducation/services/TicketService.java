package com.tsieducation.services;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.TicketsEntity;

import java.util.List;

public interface TicketService {


    List<TicketsEntity> getTicketsBySchedule(SchedulesEntity schedulesEntity);
}
