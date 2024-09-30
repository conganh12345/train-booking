package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public String index(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("page", "user");
		model.addAttribute("users", users);

		return "user/index";
	}

	@GetMapping("/base-layout")
	public String showBaseLayout(Model model) {

		model.addAttribute("page", "dashboard");
		return "layouts/base-layout";
	}
}