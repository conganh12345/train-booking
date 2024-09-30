package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Schedule;
import jakarta.validation.ValidationException;

public class ScheduleValidate {
	public void validate(Schedule schedule) {
		validateDestinationStation(schedule.getDestinationStation());
		validateScheduleName(schedule.getScheduleName());
	}

	private void validateScheduleName(String schedulename) {
		if (schedulename == null || schedulename.isEmpty()) {
			throw new ValidationException("Schedulename cannot be empty");
		}
	}

	private void validateDestinationStation(String destinationStation) {
		if (destinationStation == null || destinationStation.isEmpty()) {
			throw new ValidationException("DestinationStation cannot be empty");
		}
	}
}
