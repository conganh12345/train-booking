package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	public Payment findPaymentByPaymentMethod(String paymentMethod);
}
