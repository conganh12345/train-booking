package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("page", "dashboard");
		
		return "dashboard/index";
	}
}
