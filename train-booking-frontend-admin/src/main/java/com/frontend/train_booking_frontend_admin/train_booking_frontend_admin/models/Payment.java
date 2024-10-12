package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class Payment {
	private Integer id;
	
	@NotEmpty(message = "Số tiền không được để trống")
    @Min(value = 0, message = "Số tiền phải lớn hơn hoặc bằng 0")
    private double amount;

	@NotEmpty(message = "Trạng thái không được để trống")
    private int status;

    @NotEmpty(message = "Phương thức thanh toán không được để trống")
    private String paymentMethod;

    @NotEmpty(message = "Thời gian thanh toán không được để trống")
    private LocalDateTime paymentTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

}
