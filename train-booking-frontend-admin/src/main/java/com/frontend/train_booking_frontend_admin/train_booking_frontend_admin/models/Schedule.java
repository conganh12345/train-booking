package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

public class Schedule {
	private Integer id;

	private LocalDateTime departureDate;

	private String destinationStation;

	private String scheduleName;

	private LocalDateTime estimateArrivalDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public LocalDateTime getEstimateArrivalDate() {
		return estimateArrivalDate;
	}

	public void setEstimateArrivalDate(LocalDateTime estimateArrivalDate) {
		this.estimateArrivalDate = estimateArrivalDate;
	}
	
	
}
