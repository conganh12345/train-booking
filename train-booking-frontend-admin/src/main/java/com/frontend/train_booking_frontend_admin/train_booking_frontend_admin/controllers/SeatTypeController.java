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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.SeatTypeService;

@Controller
@RequestMapping("/seattype")
public class SeatTypeController {
	@Autowired
	private SeatTypeService userService;

	@GetMapping("/index")
	public String index(Model model) {
		List<SeatType> users = userService.getAllSeatTypes();

		model.addAttribute("page", "user").addAttribute("users", users);

		return "user/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "user");

		return "user/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() SeatType user, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (userService.addSeatType(user)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới loại ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới loại ghế thất bại!");
		}
		return "redirect:/user/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		SeatType user = userService.getSeatTypeById(id);

		model.addAttribute("page", "user").addAttribute("user", user);

		return "user/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute SeatType user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		user.setId(id);
		if (userService.updateSeatType(user)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật loại ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật loại ghế thất bại!");
		}
		return "redirect:/user/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSeatType(@PathVariable Integer id) {
		if (userService.deleteSeatType(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa loại ghế.");
		}
	}
}
