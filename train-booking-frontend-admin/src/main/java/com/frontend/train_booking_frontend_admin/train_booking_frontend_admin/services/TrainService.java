package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;

@Service
public class TrainService {
	@Value("${api.base.url}")
	private String apiUrl;
	
	public List<Train> getAllTrains(){
		// Call API to get all users
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			// Get list user from API
			Train[] users = (Train[]) restTemplate.getForObject(apiUrl + "api/train", Train[].class);
			
			return Arrays.asList(users);
		}catch (ResourceAccessException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void addTrain(Train train) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForObject(apiUrl + "api/train", train, Train.class);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
        }
    }
}
