package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Booking;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.BookingService;

@Controller
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Booking> bookings = bookingService.getAllBookings();

		model.addAttribute("page", "booking").addAttribute("bookings", bookings);

		return "booking/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "booking");

		return "booking/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() Booking booking, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("page", "booking");
			return "booking/create";
		}

		bookingService.addBooking(booking);

		return "redirect:/booking/index";
	}
}
