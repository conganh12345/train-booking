package com.backend.train_booking_backend.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.train_booking_backend.models.User;
import com.backend.train_booking_backend.service.UserService;

@RestController
@Validated
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> GetAllUser() 
	{
		return userService.getAllUsers();
	}
	
	@PostMapping("/user")
	public User AddUser(@Valid @RequestBody User user) 
	{
		return userService.addUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id) 
	{
		return userService.updateUser(id, user);
	}
	
	@GetMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username) 
	{
		return userService.getUserByUsername(username);
	}
	
	@DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Integer id) 
	{
        userService.deleteUser(id);
    }
}
