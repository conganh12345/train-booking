package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Ticket;
import jakarta.validation.ValidationException;

public class TicketValidate {
	public void validate(Ticket ticket) {
		validateTicketname(ticket.getTicketname());
		validatePrice(ticket.getPrice());
		validateStatus(ticket.getStatus());
	}

	private void validateTicketname(String ticketName) {
		if (ticketName == null || ticketName.isEmpty()) {
			throw new ValidationException("TicketName cannot be empty");
		}
	}

	private void validatePrice(double price) {
		if (price <= 0) {
			throw new ValidationException("Price must be greater than 0");
		}
	}

	private void validateStatus(int status) {
		if (status != 0 && status != 1) {
			throw new ValidationException("Status must be either 0 (closed) or 1 (open)");
		}
		if (status == 0) {
		} else if (status == 1) {
		}
	}
}
