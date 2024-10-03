package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private IUserService userService;
	
    @GetMapping("/index")
    public String index(Model model) 
    {
    	List<User> users = userService.getAllUsers();
    	
    	model.addAttribute("page", "user");
		model.addAttribute("users", users);
        
    	return "user/login2";
    } 

    @GetMapping("/base-layout")
    public String showBaseLayout() {
        return "layouts/base-layout"; 
    }
    
    @GetMapping("/login")
    public String login() {
    	return "user/login2";
    }

    @PostMapping("/check-login")
    public ResponseEntity<String> handleLogin(@RequestParam String email, @RequestParam String password) {
    	User user = userService.getUserByEmailPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok("Login successful!"); // Send success response
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password."); // Send error response
        }
    }
}
