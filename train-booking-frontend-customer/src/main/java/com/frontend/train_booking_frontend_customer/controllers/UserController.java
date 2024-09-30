package com.frontend.train_booking_frontend_customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
    @GetMapping("/index")
    public String index(Model model) 
    {
    	List<User> users = userService.getAllUsers();
    	
    	model.addAttribute("page", "user")
			.addAttribute("users", users);
        
    	return "user/index";
    } 

    @GetMapping("/base-layout")
    public String showBaseLayout() {
        return "layouts/base-layout"; 
    }

}
