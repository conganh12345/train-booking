package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Payment;
import jakarta.validation.ValidationException;

public class PaymentValidate {
	public void validate(Payment payment) {
		validateAmount(payment.getAmount());
		validateStatus(payment.getStatus());
		validatePaymentMethod(payment.getPaymentMethod());
	}

	private void validatePaymentMethod(String payment) {
		if (payment == null || payment.isEmpty()) {
			throw new ValidationException("Payment cannot be empty");
		}
	}

	private void validateStatus(int status) {
		if (status != 0 && status != 1) {
			throw new ValidationException("Status must be either 0 (closed) or 1 (open)");
		}
		if (status == 0) {
		} else if (status == 1) {
		}
	}

	private void validateAmount(double amount) {
		if (amount <= 0) {
			throw new ValidationException("Amount must be greater than 0");
		}
	}

}
