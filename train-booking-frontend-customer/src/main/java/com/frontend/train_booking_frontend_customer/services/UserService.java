package com.frontend.train_booking_frontend_customer.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.User;

@Service
public class UserService {
	@Value("${api.base.url}")
	private String apiUrl;
	
	public List<User> getAllUsers(){
		// Call API to get all users
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			// Get list user from API
			User[] users = (User[]) restTemplate.getForObject(apiUrl + "api/user", User[].class);
			
			return Arrays.asList(users);
		}catch (ResourceAccessException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
