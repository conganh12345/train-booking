package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.SeatType;

@Service
public class SeatTypeService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<SeatType> getAllSeatTypes() {
		// Call API to get all seatTypes
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list seatType from API
			SeatType[] seatTypes = (SeatType[]) restTemplate.getForObject(apiUrl + "api/seatType", SeatType[].class);

			return Arrays.asList(seatTypes);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSeatType(SeatType seatType) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/seatType", seatType, SeatType.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public SeatType getSeatTypeById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/seatType/id/" + id, SeatType.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateSeatType(SeatType seatType) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/seatType/" + seatType.getId(), seatType);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteSeatType(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/seatType/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
