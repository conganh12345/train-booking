package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Ticket;

@Service
public class TicketService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<Ticket> getAllTickets() {
		// Call API to get all tickets
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list ticket from API
			Ticket[] tickets = (Ticket[]) restTemplate.getForObject(apiUrl + "api/ticket", Ticket[].class);

			return Arrays.asList(tickets);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addTicket(Ticket ticket) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/ticket", ticket, Ticket.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Ticket getTicketById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/ticket/id/" + id, Ticket.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateTicket(Ticket ticket) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/ticket/" + ticket.getId(), ticket);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTicket(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/ticket/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
