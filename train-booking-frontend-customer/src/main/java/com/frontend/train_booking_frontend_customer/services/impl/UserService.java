package com.frontend.train_booking_frontend_customer.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.frontend.train_booking_frontend_customer.models.User;
import com.frontend.train_booking_frontend_customer.services.IUserService;

@Service
public class UserService implements IUserService{
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
	
	public User getUserByEmailPassword(String email, String password) {
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        String url = apiUrl + "api/user/findByEmailAndPassword/{email}/{password}";
	        Map<String, String> params = new HashMap<>();
	        params.put("email", email);
	        params.put("password", password);
	        
	        // Call API
	        User user = restTemplate.getForObject(url, User.class, params);
	        
	        // check result
	        return user; 
	    } catch (HttpClientErrorException e) {
	        // Cannot to find any account
	        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
	            return null; 
	        } else {
	            e.printStackTrace();
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
}