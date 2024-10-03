package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.TicketBookingDetail;

public interface ITicketBookingDetailService {
	List<TicketBookingDetail> getAllTicketBookingDetails();

	TicketBookingDetail getTicketBookingDetail(Integer id);

	TicketBookingDetail addTicketBookingDetail(TicketBookingDetail ticketbookingdetail);

	TicketBookingDetail updateTicketBookingDetail(Integer id, TicketBookingDetail ticketbookingdetail);

	TicketBookingDetail getTicketBookingDetailByCustomerType(String customertype);

	Optional<TicketBookingDetail> deleteTicketBookingDetail(Integer id);
}
