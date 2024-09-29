package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.Payment;

public interface IPaymentService {
	List<Payment> getAllPayments();

	Payment getPayment(Integer id);

	Payment addPayment(Payment payment);

	Payment updatePayment(Integer id, Payment payment);

	Payment getPaymentByPaymentMethod(String paymentMethod);

	List<Payment> deletePayment(Integer[] ids);
}
