package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.exception.TicketBookingDetailValidate;
import com.backend.train_booking_backend.models.TicketBookingDetail;
import com.backend.train_booking_backend.repositories.TicketBookingDetailRepository;
import com.backend.train_booking_backend.services.ITicketBookingDetailService;

@Service
public class TicketBookingDetailService implements ITicketBookingDetailService {

	@Autowired
	private TicketBookingDetailRepository ticketBookingDetailRepositoryRepo;

	@Override
	public List<TicketBookingDetail> getAllTicketBookingDetails() {
		return ticketBookingDetailRepositoryRepo.findAll();
	}

	@Override
	public TicketBookingDetail getTicketBookingDetail(Integer id) {
		return ticketBookingDetailRepositoryRepo.findById(id).get();
	}

	@Override
	public TicketBookingDetail addTicketBookingDetail(TicketBookingDetail ticketbookingdetail) {
		try {
			return ticketBookingDetailRepositoryRepo.save(ticketbookingdetail);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm chi tiết vé.", e);
		}
	}

	@Override
	public TicketBookingDetail updateTicketBookingDetail(Integer id, TicketBookingDetail ticketbookingdetail) {
		try {
			Optional<TicketBookingDetail> oldTicketBookingDetailOpt = ticketBookingDetailRepositoryRepo.findById(id);
			if (oldTicketBookingDetailOpt.isPresent()) {
				ticketbookingdetail.setId(id);
			}
			return ticketBookingDetailRepositoryRepo.save(ticketbookingdetail);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa chi tiết vé.", e);
		}
	}

	@Override
	public TicketBookingDetail getTicketBookingDetailByCustomerType(String customertype) {
		return ticketBookingDetailRepositoryRepo.findTicketBookingDetailByCustomerType(customertype);
	}

	@Override
	@Transactional
	public Optional<TicketBookingDetail> deleteTicketBookingDetail(Integer id) {
	    try {
	        Optional<TicketBookingDetail> ticketBookingDetailRepositoryOpt = ticketBookingDetailRepositoryRepo.findById(id);
	        if (ticketBookingDetailRepositoryOpt.isPresent()) {
	        	TicketBookingDetail user = ticketBookingDetailRepositoryOpt.get();
	        	ticketBookingDetailRepositoryRepo.deleteById(id);
	            return Optional.of(user); 
	        } else {
	            System.out.println("Ticket Booking Detail with ID " + id + " not found.");
	            return Optional.empty();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa chi tiết vé.", e);
	    }
	}
}
