package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Station;
import jakarta.validation.ValidationException;

public class StationValidate 
{
	public void validate(Station station)
	{
		validateStationname(station.getStationname());
		validateDescription(station.getDescription());
	    validateAddress(station.getAddress());
	}
	
	private void validateStationname(String stationname)
	{
		if (stationname == null || stationname.isEmpty()) {
            throw new ValidationException("Stationname cannot be empty");
        }
	}
	
	private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new ValidationException("Description cannot be empty");
        }
    }
	
	private void validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new ValidationException("Address cannot be empty");
        }
    }
}
