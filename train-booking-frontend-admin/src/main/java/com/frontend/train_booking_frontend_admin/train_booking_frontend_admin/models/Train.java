package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

public class Train {
	private Integer id;
	private String trainname;
	private int coachcount;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainname() {
		return trainname;
	}
	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}
	public int getCoachcount() {
		return coachcount;
	}
	public void setCoachcount(int coachcount) {
		this.coachcount = coachcount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
