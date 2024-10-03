package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.Payment;
import com.backend.train_booking_backend.repositories.PaymentRepository;
import com.backend.train_booking_backend.services.IPaymentService;

@Service
public class PaymentService implements IPaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepo.findAll();
	}

	@Override
	public Payment getPayment(Integer id) {
		return paymentRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Payment addPayment(Payment payment) {
		try {
			payment.setPaymentTime(LocalDateTime.now());

			return paymentRepo.save(payment);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm thanh toán.", e);
		}
	}

	@Override
	public Payment updatePayment(Integer id, Payment payment) {
		try {
			Optional<Payment> oldPaymentOpt = paymentRepo.findById(id);
			if (oldPaymentOpt.isPresent()) {
				payment.setId(id);
				payment.setPaymentTime(oldPaymentOpt.get().getPaymentTime());
			}
			return paymentRepo.save(payment);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa thanh toán.", e);
		}
	}

	@Override
	public Payment getPaymentByPaymentMethod(String paymentMethod) {
		return paymentRepo.findPaymentByPaymentMethod(paymentMethod);
	}

	@Override
	@Transactional
	public Optional<Payment> deletePayment(Integer id) {
	    try {
	        Optional<Payment> paymentOpt = paymentRepo.findById(id);
	        if (paymentOpt.isPresent()) {
	        	Payment payment = paymentOpt.get();
	        	paymentRepo.deleteById(id);
	            return Optional.of(payment); 
	        } else {
	            System.out.println("Payment with ID " + id + " not found.");
	            return Optional.empty();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa thanh toán.", e);
	    }
	}
}
