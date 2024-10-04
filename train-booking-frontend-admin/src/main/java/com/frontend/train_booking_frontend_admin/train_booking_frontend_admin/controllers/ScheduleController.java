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
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Schedule;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Schedule> schedules = scheduleService.getAllSchedules();

		model.addAttribute("page", "schedule").addAttribute("schedules", schedules);

		return "schedule/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "schedule");

		return "schedule/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() Schedule schedule, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (scheduleService.addSchedule(schedule)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới lịch trình thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới lịch trình thất bại!");
		}
		return "redirect:/schedule/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Schedule schedule = scheduleService.getScheduleById(id);

		model.addAttribute("page", "schedule").addAttribute("schedule", schedule);

		return "schedule/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute Schedule schedule, BindingResult result,
			RedirectAttributes redirectAttributes) {
		schedule.setId(id);
		if (scheduleService.updateSchedule(schedule)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật lịch trình thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật lịch trình thất bại!");
		}
		return "redirect:/schedule/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable Integer id) {
		if (scheduleService.deleteschedule(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa lịch trình.");
		}
	}
}
