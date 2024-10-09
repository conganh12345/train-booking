package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.User;

@Service
public class UserService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<User> getAllUsers() {
		// Call API to get all users
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list user from API
			User[] users = (User[]) restTemplate.getForObject(apiUrl + "api/user", User[].class);

			return Arrays.asList(users);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(User user) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/user", user, User.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getUserById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/user/id/" + id, User.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateUser(User user) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/user/" + user.getId(), user);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/user/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	public User getUserByEmail(String email) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			String url = apiUrl + "api/user/findByEmail/{email}";
			Map<String, String> params = new HashMap<>();
			params.put("email", email);
			User user = restTemplate.getForObject(url, User.class, params);

			return user;

		} catch (HttpClientErrorException e) {
			// Cannot to find any account
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			} else {
				e.printStackTrace();
				throw new RuntimeException("Failed to fetch user: " + e.getMessage());
			}
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

	public User signUp(User user) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			String url = apiUrl + "api/user";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

			HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
			ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, requestEntity, User.class);

			if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
				return responseEntity.getBody();
			} else {
				throw new RuntimeException("Failed to sign up: " + responseEntity.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw new RuntimeException("Error during sign up: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}