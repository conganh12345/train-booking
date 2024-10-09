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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.TicketBookingDetail;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TicketBookingDetailService;

@Controller
@RequestMapping("/ticketbookingdetail")
public class TicketBookingDetailController {
	@Autowired
	private TicketBookingDetailService ticketBookingDetailService;

	@GetMapping("/index")
	public String index(Model model) {
		List<TicketBookingDetail> ticketBookingDetails = ticketBookingDetailService.getAllTicketBookingDetails();

		model.addAttribute("page", "ticketBookingDetail").addAttribute("ticketBookingDetails", ticketBookingDetails);

		return "ticketBookingDetail/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "ticketBookingDetail");

		return "ticketBookingDetail/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() TicketBookingDetail ticketBookingDetail, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (ticketBookingDetailService.addTicketBookingDetail(ticketBookingDetail)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới chi tiết vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới chi tiết vé thất bại!");
		}
		return "redirect:/ticketBookingDetail/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		TicketBookingDetail ticketBookingDetail = ticketBookingDetailService.getTicketBookingDetailById(id);

		model.addAttribute("page", "ticketBookingDetail").addAttribute("ticketBookingDetail", ticketBookingDetail);

		return "ticketBookingDetail/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute TicketBookingDetail ticketBookingDetail,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ticketBookingDetail.setId(id);
		if (ticketBookingDetailService.updateTicketBookingDetail(ticketBookingDetail)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật chi tiết vé thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật chi tiết vé thất bại!");
		}
		return "redirect:/ticketBookingDetail/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTicketBookingDetail(@PathVariable Integer id) {
		if (ticketBookingDetailService.deleteTicketBookingDetail(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa chi tiết vé.");
		}
	}
}
