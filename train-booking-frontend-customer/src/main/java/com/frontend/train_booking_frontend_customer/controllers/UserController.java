package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Console;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/index")
	public String index(Model model) {
		List<User> users = userService.getAllUsers();

		model.addAttribute("page", "user").addAttribute("users", users);

		return "user/signIn";
	}

	@GetMapping("/base-layout")
	public String showBaseLayout() {
		return "redirect:/dashboard/index";
	}

	@GetMapping("/login")
	public String login() {
		return "user/signIn";
	}

	@PostMapping("/check-login")
	public ResponseEntity<String> handleLogin(@RequestParam String email, @RequestParam String password) {
		User user = userService.getUserByEmailPassword(email, password);
		if (user != null) {
			return ResponseEntity.ok("Đăng nhập thành công!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai email hoặc mật khẩu.");
		}
	}

	@PostMapping("/check-email-exist")
	public ResponseEntity<String> checkEmailExist(@RequestParam String email) {
		User user = userService.getUserByEmail(email);
		if (user != null) {
			return ResponseEntity.ok("Email đã tồn tại");
		} else {
			return null;
		}
	}

	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		userService.signUp(user);
		return "user/signIn";
	}

}
