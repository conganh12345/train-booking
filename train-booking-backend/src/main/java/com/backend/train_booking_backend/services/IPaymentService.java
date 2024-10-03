package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Payment;

public interface IPaymentService {
	List<Payment> getAllPayments();

	Payment getPayment(Integer id);

	Payment addPayment(Payment payment);

	Payment updatePayment(Integer id, Payment payment);

	Payment getPaymentByPaymentMethod(String paymentMethod);

	Optional<Payment> deletePayment(Integer id);
}
