package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Coach;
import jakarta.validation.ValidationException;

public class CoachValidate {
	public void validate(Coach coach) {
		validateCoachName(coach.getCoachName());
		validateSeatCount(coach.getSeatCount());
		validateDescription(coach.getDescription());
	}

	private void validateCoachName(String coachname) {
		if (coachname == null || coachname.isEmpty()) {
			throw new ValidationException("Coachname cannot be empty");
		}
	}

	private void validateDescription(String description) {
		if (description == null || description.isEmpty()) {
			throw new ValidationException("Description cannot be empty");
		}
	}

	private void validateSeatCount(int seatCount) {
		if (seatCount <= 0) {
			throw new ValidationException("Seat count must be greater than 0");
		}
	}
}
