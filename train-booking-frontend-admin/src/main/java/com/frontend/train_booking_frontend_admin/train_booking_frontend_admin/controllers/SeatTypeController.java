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
	private SeatTypeService seattypeService;

	@GetMapping("/index")
	public String index(Model model) {
		List<SeatType> seattypes = seattypeService.getAllSeatTypes();

		model.addAttribute("page", "seattype").addAttribute("seattypes", seattypes);

		return "seattype/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "seattype");

		return "seattype/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() SeatType seattype, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (seattypeService.addSeatType(seattype)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới ghế thất bại!");
		}
		return "redirect:/seattype/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		SeatType seattype = seattypeService.getSeatTypeById(id);

		model.addAttribute("page", "seattype").addAttribute("seattype", seattype);

		return "seattype/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute SeatType seattype, BindingResult result,
			RedirectAttributes redirectAttributes) {
		seattype.setId(id);
		if (seattypeService.updateSeatType(seattype)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật ghế thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật ghế thất bại!");
		}
		return "redirect:/seattype/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSeattype(@PathVariable Integer id) {
		if (seattypeService.deleteSeatType(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa tàu.");
		}
	}
}
