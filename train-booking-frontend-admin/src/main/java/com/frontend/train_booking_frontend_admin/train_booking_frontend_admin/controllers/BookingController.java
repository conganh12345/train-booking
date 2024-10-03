package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String create(@ModelAttribute() Booking booking, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (bookingService.addBooking(booking)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới đặt vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới đặt vé thất bại!");
		}
		return "redirect:/booking/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Booking booking = bookingService.getBookingById(id);

		model.addAttribute("page", "booking").addAttribute("booking", booking);

		return "booking/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute Booking booking, BindingResult result,
			RedirectAttributes redirectAttributes) {
		booking.setId(id);
		if (bookingService.updateBooking(booking)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật đặt vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật đặt vé thất bại!");
		}
		return "redirect:/booking/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBooking(@PathVariable Integer id) {
		if (bookingService.deleteBooking(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể vé đặt.");
		}
	}
}
