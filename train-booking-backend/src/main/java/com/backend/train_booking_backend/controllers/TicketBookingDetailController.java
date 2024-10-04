package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.backend.train_booking_backend.exception.TicketBookingDetailValidate;
import com.backend.train_booking_backend.models.TicketBookingDetail;
import com.backend.train_booking_backend.services.ITicketBookingDetailService;

@RestController
@Validated
@RequestMapping("/api/ticketbookingdetail")
public class TicketBookingDetailController {
	@Autowired
	private ITicketBookingDetailService ticketbookingdetailService;
	private TicketBookingDetailValidate validation = new TicketBookingDetailValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<TicketBookingDetail>> getAllTicketBookingDetail() {
		List<TicketBookingDetail> ticketbookingdetail = ticketbookingdetailService.getAllTicketBookingDetails();
		if (ticketbookingdetail.isEmpty()) {
			ticketbookingdetail = new ArrayList<TicketBookingDetail>();
		}
		return new ResponseEntity<>(ticketbookingdetail, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<TicketBookingDetail> getTicketBookingDetail(@PathVariable Integer id) {
		TicketBookingDetail ticketbookingdetail = ticketbookingdetailService.getTicketBookingDetail(id);
		if (ticketbookingdetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticketbookingdetail, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TicketBookingDetail> addTicketBookingDetail(
			@RequestBody TicketBookingDetail ticketbookingdetail) {
		validation.validate(ticketbookingdetail);
		TicketBookingDetail createdTicketBookingDetail = ticketbookingdetailService
				.addTicketBookingDetail(ticketbookingdetail);
		return new ResponseEntity<>(createdTicketBookingDetail, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TicketBookingDetail> updateTicketBookingDetail(
			@RequestBody TicketBookingDetail ticketbookingdetail, @PathVariable Integer id) {
		validation.validate(ticketbookingdetail);
		TicketBookingDetail updatedTicketBookingDetail = ticketbookingdetailService.updateTicketBookingDetail(id,
				ticketbookingdetail);
		if (updatedTicketBookingDetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedTicketBookingDetail, HttpStatus.OK);
	}

	@GetMapping("/{customertype}")
	public ResponseEntity<TicketBookingDetail> getTicketBookingDetail(@PathVariable String customertype) {
		TicketBookingDetail ticketbookingdetail = ticketbookingdetailService
				.getTicketBookingDetailByCustomerType(customertype);
		if (ticketbookingdetail == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticketbookingdetail, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TicketBookingDetail> deleteTicketBookingDetail(@PathVariable Integer id) {
		Optional<TicketBookingDetail> deletedTicketBookingDetail = ticketbookingdetailService
				.deleteTicketBookingDetail(id);
		if (deletedTicketBookingDetail.isPresent()) {
			return new ResponseEntity<>(deletedTicketBookingDetail.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
