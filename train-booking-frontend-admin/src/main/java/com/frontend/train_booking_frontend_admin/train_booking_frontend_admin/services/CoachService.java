package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Coach;

@Service
public class CoachService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<Coach> getAllCoachs() {
		// Call API to get all coachs
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list coach from API
			Coach[] coachs = (Coach[]) restTemplate.getForObject(apiUrl + "api/coach", Coach[].class);

			return Arrays.asList(coachs);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addCoach(Coach coach) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/coach", coach, Coach.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Coach getCoachById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/coach/id/" + id, Coach.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateCoach(Coach coach) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/coach/" + coach.getId(), coach);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCoach(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/coach/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
