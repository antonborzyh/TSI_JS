package com.tsieducation.services.impl;

import com.tsieducation.entities.SchedulesEntity;
import com.tsieducation.entities.TicketsEntity;
import com.tsieducation.repositories.TicketRepository;
import com.tsieducation.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Override
    public List<TicketsEntity> getTicketsBySchedule(SchedulesEntity schedulesEntity) {
        return ticketRepository.findByScheduleByScheduleId(schedulesEntity);
    }
}
