package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.backend.train_booking_backend.exception.ScheduleValidate;
import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.services.IScheduleService;

@RestController
@Validated
@RequestMapping("/api/schedule")
public class ScheduleController {
	@Autowired
	private IScheduleService scheduleService;
	private ScheduleValidate validation = new ScheduleValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Schedule>> getAllSchedule() {
		List<Schedule> schedule = scheduleService.getAllSchedules();
		if (schedule.isEmpty()) {
			schedule = new ArrayList<Schedule>();
		}
		return new ResponseEntity<>(schedule, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Schedule> getSchedule(@PathVariable Integer id) {
		Schedule schedule = scheduleService.getSchedule(id);
		if (schedule == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(schedule, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
		validation.validate(schedule);
		Schedule createdSchedule = scheduleService.addSchedule(schedule);
		return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule, @PathVariable Integer id) {
		validation.validate(schedule);
		Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
		if (updatedSchedule == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
	}

	@GetMapping("/{schedulename}")
	public ResponseEntity<Schedule> getScheduleByScheduleName(@PathVariable String schedule) {
		Schedule schedules = scheduleService.getScheduleByScheduleName(schedule);
		if (schedules == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(schedules, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<List<Schedule>> deleteSchedule(@RequestBody Integer[] ids) {
		List<Schedule> deletedSchedules = scheduleService.deleteSchedule(ids);
		if (deletedSchedules.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(deletedSchedules, HttpStatus.OK);
	}
}
