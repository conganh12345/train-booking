package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Booking;
import jakarta.validation.ValidationException;

public class BookingValidate {
	public void validate(Booking booking) {
		validateFullName(booking.getFullName());
		validatePaymentMethod(booking.getPaymentMethod());
		validateStatus(booking.getStatus());
		validateDepatureStation(booking.getDepatureStation());
		validateDestinationStation(booking.getDestinationStation());
	}

	private void validateFullName(String fullname) {
		if (fullname == null || fullname.isEmpty()) {
			throw new ValidationException("Fullname cannot be empty");
		}
	}

	private void validatePaymentMethod(int paymentMethod) {
		// Giả sử có các mã phương thức thanh toán hợp lệ như sau:
		// 1 - Thẻ tín dụng, 2 - Thẻ ghi nợ, 3 - Ví điện tử, 4 - Tiền mặt
		if (paymentMethod < 1 || paymentMethod > 4) {
			throw new ValidationException(
					"Invalid payment method. Accepted values: 1 (Credit card), 2 (Debit card), 3 (E-wallet), 4 (Cash)");
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

	private void validateDepatureStation(String depatureStation) {
		if (depatureStation == null || depatureStation.isEmpty()) {
			throw new ValidationException("Depature station cannot be empty");
		}
	}

	private void validateDestinationStation(String destinationStation) {
		if (destinationStation == null || destinationStation.isEmpty()) {
			throw new ValidationException("Destination station cannot be empty");
		}
	}
}
