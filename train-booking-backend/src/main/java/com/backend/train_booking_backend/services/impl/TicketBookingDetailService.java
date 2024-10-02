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
	public List<TicketBookingDetail> deleteTicketBookingDetail(Integer[] ids) {
		List<TicketBookingDetail> deletes = new ArrayList<>();

		try {
			for (Integer id : ids) {
				Optional<TicketBookingDetail> ticketbookingdetailOpt = ticketBookingDetailRepositoryRepo.findById(id);
				if (ticketbookingdetailOpt.isPresent()) {
					TicketBookingDetail ticketbookingdetail = ticketbookingdetailOpt.get();
					deletes.add(ticketbookingdetail);
					ticketBookingDetailRepositoryRepo.deleteById(id);
				} else {
					System.out.println("TicketBookingDetail with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa chi tiết vé.", e);
		}
		return deletes;
	}
}
