package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Station;

@Service

public class StationService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<Station> getAllStations() {
		// Call API to get all stations
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list station from API
			Station[] stations = (Station[]) restTemplate.getForObject(apiUrl + "api/station", Station[].class);

			return Arrays.asList(stations);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addStation(Station station) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/station", station, Station.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Station getStationById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/station/id/" + id, Station.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateStation(Station station) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/station/" + station.getId(), station);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteStation(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/station/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
