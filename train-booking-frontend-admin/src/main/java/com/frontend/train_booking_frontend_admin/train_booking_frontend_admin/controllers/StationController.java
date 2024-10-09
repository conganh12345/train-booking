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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Station;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.StationService;

@Controller
@RequestMapping("/station")
public class StationController {
	@Autowired
	private StationService stationService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Station> stations = stationService.getAllStations();

		model.addAttribute("page", "station").addAttribute("stations", stations);

		return "station/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "station");

		return "station/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() Station station, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (stationService.addStation(station)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới nhà ga thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới nhà ga thất bại!");
		}
		return "redirect:/station/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Station station = stationService.getStationById(id);

		model.addAttribute("page", "station").addAttribute("station", station);

		return "station/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute Station station, BindingResult result,
			RedirectAttributes redirectAttributes) {
		station.setId(id);
		if (stationService.updateStation(station)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật nhà ga thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật nhà ga thất bại!");
		}
		return "redirect:/station/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletestation(@PathVariable Integer id) {
		if (stationService.deleteStation(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa nhà ga.");
		}
	}
}
