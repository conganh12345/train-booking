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
import com.backend.train_booking_backend.exception.AdminValidate;
import com.backend.train_booking_backend.models.Admin;
import com.backend.train_booking_backend.services.IAdminService;

@RestController
@Validated
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	private AdminValidate validation = new AdminValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Admin>> getAllAdmin() {
		List<Admin> admins = adminService.getAllAdmins();
		if (admins.isEmpty()) {
			admins = new ArrayList<Admin>();
		}
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Integer id) {
		Admin admin = adminService.getAdmin(id);
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		validation.validate(admin);
		Admin createdAdmin = adminService.addAdmin(admin);
		return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable Integer id) {
		validation.validate(admin);
		Admin updatedAdmin = adminService.updateAdmin(id, admin);
		if (updatedAdmin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	}

	@GetMapping("/{adminname}")
	public ResponseEntity<Admin> getAdminbyAdminname(@PathVariable String adminname) {
		Admin admin = adminService.getAdminByAdminname(adminname);
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer id) {
		Optional<Admin> deletedAdmin = adminService.deleteAdmin(id);
		if (deletedAdmin.isPresent()) {
			return new ResponseEntity<>(deletedAdmin.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
