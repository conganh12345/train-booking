package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Booking;

@Service
public class BookingService {
	@Value("${api.base.url}")
	private String apiUrl;
	
	public List<Booking> getAllBookings(){
		// Call API to get all bookings
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			// Get list booking from API
			Booking[] bookings = (Booking[]) restTemplate.getForObject(apiUrl + "api/booking", Booking[].class);
			
			return Arrays.asList(bookings);
		}catch (ResourceAccessException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addBooking(Booking booking) {
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        restTemplate.postForObject(apiUrl + "api/booking", booking, Booking.class);
	        return true;  
	    } catch (ResourceAccessException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	public Booking getBookingById(Integer id) { 
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiUrl + "api/booking/id/" + id, Booking.class);
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
	 
	 public boolean updateBooking(Booking booking) { 
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(apiUrl + "api/booking/" + booking.getId(), booking);
            return true;
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
	 
	 public boolean deleteBooking(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(apiUrl + "api/booking/" + id);
            return true; 
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return false; 
        }
    }
}
