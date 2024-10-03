package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.train_booking_backend.exception.PaymentValidate;
import com.backend.train_booking_backend.models.Payment;
import com.backend.train_booking_backend.services.IPaymentService;

@RestController
@Validated
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private IPaymentService paymentService;
	private PaymentValidate validation = new PaymentValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayment() {
		List<Payment> payment = paymentService.getAllPayments();
		if (payment.isEmpty()) {
			payment = new ArrayList<Payment>();
		}
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable Integer id) {
		Payment payment = paymentService.getPayment(id);
		if (payment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Payment> addPayment(@RequestBody Payment payment) {
		validation.validate(payment);
		Payment createdPayment = paymentService.addPayment(payment);
		return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatepayment(@RequestBody Payment payment, @PathVariable Integer id) {
		validation.validate(payment);
		Payment updatedPayment = paymentService.updatePayment(id, payment);
		if (updatedPayment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
	}

	@GetMapping("/{paymentmethod}")
	public ResponseEntity<Payment> getPaymentByPaymentMethod(@PathVariable String paymentMethod) {
		Payment payment = paymentService.getPaymentByPaymentMethod(paymentMethod);
		if (payment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Payment> deletePayment(@PathVariable Integer id) {
	    Optional<Payment> deletedPayment = paymentService.deletePayment(id); 
	    if (deletedPayment.isPresent()) {
	        return new ResponseEntity<>(deletedPayment.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
