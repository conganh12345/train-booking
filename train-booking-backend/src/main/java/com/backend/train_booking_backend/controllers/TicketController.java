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
import com.backend.train_booking_backend.exception.TicketValidate;
import com.backend.train_booking_backend.models.Ticket;
import com.backend.train_booking_backend.services.ITicketService;

@RestController
@Validated
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	private TicketValidate validation = new TicketValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Ticket>> getAllTicket() {
		List<Ticket> tickets = ticketService.getAllTickets();
		if (tickets.isEmpty()) {
			tickets = new ArrayList<Ticket>();
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Ticket> getSeatType(@PathVariable Integer id) {
		Ticket ticket = ticketService.getTicket(id);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
		validation.validate(ticket);
		Ticket createdSeatType = ticketService.addTicket(ticket);
		return new ResponseEntity<>(createdSeatType, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Integer id) {
		validation.validate(ticket);
		Ticket updatedTicket = ticketService.updateTicket(id, ticket);
		if (updatedTicket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
	}

	@GetMapping("/{ticketname}")
	public ResponseEntity<Ticket> getSeatTypeBySeatTypename(@PathVariable String ticketName) {
		Ticket ticket = ticketService.getTicketByTicketName(ticketName);
		if (ticket == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable Integer id) {
		Optional<Ticket> deletedTicket = ticketService.deleteTicket(id);
		if (deletedTicket.isPresent()) {
			return new ResponseEntity<>(deletedTicket.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
