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
		// Call API to get all Trains
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			// Get list train from API
			Train[] trains = (Train[]) restTemplate.getForObject(apiUrl + "api/train", Train[].class);
			
			return Arrays.asList(trains);
		}catch (ResourceAccessException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addTrain(Train train) {
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        restTemplate.postForObject(apiUrl + "api/train", train, Train.class);
	        return true;  
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	 public Train getTrainById(Integer id) { 
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiUrl + "api/train/id/" + id, Train.class);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
	 
	 public boolean updatetrain(Train train) { 
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(apiUrl + "api/train/" + train.getId(), train);
            return true;
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
	 
	 public boolean deletetrain(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(apiUrl + "api/train/" + id);
            return true; 
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false; 
        }
    }
}
