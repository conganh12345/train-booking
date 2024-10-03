package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

public class SeatType {
	private Integer id;

	private String seatTypeName;

	private int seatPosition;

	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeatTypeName() {
		return seatTypeName;
	}

	public void setSeatTypeName(String seatTypeName) {
		this.seatTypeName = seatTypeName;
	}

	public int getSeatPosition() {
		return seatPosition;
	}

	public void setSeatPosition(int seatPosition) {
		this.seatPosition = seatPosition;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
