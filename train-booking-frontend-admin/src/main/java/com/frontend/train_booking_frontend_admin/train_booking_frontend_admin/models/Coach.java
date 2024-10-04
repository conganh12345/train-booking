package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

public class Coach {
	private Integer id;
	private String coachName;
	private int seatCount;
	private String description;
	private Integer trainId;

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

	 public Integer getTrainId() { 
        return trainId;
    }

    public void setTrainId(Integer trainId) { 
        this.trainId = trainId;
    }
}
