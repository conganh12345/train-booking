package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.TicketBookingDetail;
import jakarta.validation.ValidationException;

public class TicketBookingDetailValidate {
	public void validate(TicketBookingDetail ticketbookingdetail) {
		validateCustomerType(ticketbookingdetail.getCustomerType());
		validatePrice(ticketbookingdetail.getPrice());
	}
	
	private void validateCustomerType(String customertype) {
		if (customertype == null || customertype.isEmpty()) {
			throw new ValidationException("Customertype cannot be empty");
		}
	}

	private void validatePrice(double price) {
		if (price <= 0) {
			throw new ValidationException("Price must be greater than 0");
		}
	}

}
