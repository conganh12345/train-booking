package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.TicketBookingDetail;

@Service
public class TicketBookingDetailService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<TicketBookingDetail> getAllTicketBookingDetails() {
		// Call API to get all ticketBookingDetails
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list ticketBookingDetail from API
			TicketBookingDetail[] ticketBookingDetails = (TicketBookingDetail[]) restTemplate
					.getForObject(apiUrl + "api/ticketBookingDetail", TicketBookingDetail[].class);

			return Arrays.asList(ticketBookingDetails);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addTicketBookingDetail(TicketBookingDetail ticketBookingDetail) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/ticketBookingDetail", ticketBookingDetail,
					TicketBookingDetail.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public TicketBookingDetail getTicketBookingDetailById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/ticketBookingDetail/id/" + id, TicketBookingDetail.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateTicketBookingDetail(TicketBookingDetail ticketBookingDetail) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/ticketBookingDetail/" + ticketBookingDetail.getId(), ticketBookingDetail);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTicketBookingDetail(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/ticketBookingDetail/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
