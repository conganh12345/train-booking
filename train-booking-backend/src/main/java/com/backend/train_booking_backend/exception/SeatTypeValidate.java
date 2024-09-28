package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.SeatType;
import jakarta.validation.ValidationException;

public class SeatTypeValidate
{
	public void validate(SeatType seattype) 
	{
	    validateSeattypename(seattype.getSeatTypeName());
	    validateSeatposition(seattype.getSeatPosition());
	    validateStatus(seattype.getStatus());
	}
	
	private void validateSeattypename(String seatTypeName) {
        if (seatTypeName == null || seatTypeName.isEmpty()) {
            throw new ValidationException("seatTypeName cannot be empty");
        }
    }
	
	private void validateSeatposition(int seatPosition) {
	    checkSeatPositionRange(seatPosition);
	}

	private void checkSeatPositionRange(int seatPosition) {
	    if (seatPosition <= 0) {
	        throw new ValidationException("Seat position must be greater than 0");
	    }

	    int maxSeatPosition = 100;
	    if (seatPosition > maxSeatPosition) {
	        throw new ValidationException("Seat position must be between 1 and " + maxSeatPosition);
	    }
	}

	private void validateStatus(int status) {
	    if (status != 0 && status != 1) {
	        throw new ValidationException("Status must be either 0 (closed) or 1 (open)");
	    }
	    if (status == 0) {
	    } 
	    else if (status == 1) {
	    }
	}
}
