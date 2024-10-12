package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SeatType {
	private Integer id;

	@NotEmpty(message = "Tên loại ghế không được để trống")
    @Size(min = 3, max = 50, message = "Tên loại ghế phải có độ dài từ 3 đến 50 ký tự")
    private String seatTypeName;

	@NotEmpty(message = "Vị trí ghế không được để trống")
    @Min(value = 1, message = "Vị trí ghế phải lớn hơn hoặc bằng 1")
    private int seatPosition;

	@NotEmpty(message = "Trạng thái không được để trống")
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
