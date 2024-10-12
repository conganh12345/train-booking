package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Coach {
	private Integer id;
	
	@NotEmpty(message = "Tên toa không được để trống")
    @Size(min = 3, max = 50, message = "Tên toa phải có độ dài từ 3 đến 50 ký tự")
    private String coachName;

	@NotEmpty(message = "Số lượng ghế không được để trống")
    @Min(value = 1, message = "Số lượng ghế phải lớn hơn hoặc bằng 1")
    private int seatCount;

    @Size(max = 200, message = "Mô tả không được dài quá 200 ký tự")
    private String description;

    @NotEmpty(message = "Toa tàu phải liên kết với một tàu")
    private Train train;  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
