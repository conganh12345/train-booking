package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Payment;

@Service
public class PaymentService {
	@Value("${api.base.url}")
	private String apiUrl;

	public List<Payment> getAllPayments() {
		// Call API to get all Payment
		RestTemplate restTemplate = new RestTemplate();

		try {
			// Get list payment from API
			Payment[] payments = (Payment[]) restTemplate.getForObject(apiUrl + "api/payment", Payment[].class);

			return Arrays.asList(payments);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addPayment(Payment payment) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.postForObject(apiUrl + "api/payment", payment, Payment.class);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Payment getPaymentById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(apiUrl + "api/payment/id/" + id, Payment.class);
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updatePayment(Payment payment) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(apiUrl + "api/payment/" + payment.getId(), payment);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletePayment(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(apiUrl + "api/payment/" + id);
			return true;
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
