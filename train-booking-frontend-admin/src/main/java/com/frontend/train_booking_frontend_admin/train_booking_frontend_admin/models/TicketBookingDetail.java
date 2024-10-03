package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

public class TicketBookingDetail {
	private Integer id;

	private String customerType;

	private double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
