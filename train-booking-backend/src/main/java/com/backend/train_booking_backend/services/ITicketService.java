package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Ticket;

public interface ITicketService {
	List<Ticket> getAllTickets();

	Ticket getTicket(Integer id);

	Ticket addTicket(Ticket ticket);

	Ticket updateTicket(Integer id, Ticket ticket);

	Ticket getTicketByTicketName(String ticketname);

	boolean deleteTicket(Integer ids);
}
