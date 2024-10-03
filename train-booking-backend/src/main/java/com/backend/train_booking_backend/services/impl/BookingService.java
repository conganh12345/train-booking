package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.Booking;
import com.backend.train_booking_backend.repositories.BookingRepository;
import com.backend.train_booking_backend.services.IBookingService;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public Booking getBooking(Integer id) {
		return bookingRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Booking addBooking(Booking booking) {
		try {
			booking.setBookingTime(LocalDateTime.now());

			return bookingRepo.save(booking);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm đặt vé.", e);
		}
	}

	@Override
	@Transactional
	public Booking updateBooking(Integer id, Booking booking) {
		try {
			Optional<Booking> oldBookingOpt = bookingRepo.findById(id);
			if (oldBookingOpt.isPresent()) {
				booking.setId(id);
				booking.setBookingTime(LocalDateTime.now());
			}
			return bookingRepo.save(booking);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa đặt vé.", e);
		}
	}

	@Override
	public Booking getBookingByFullName(String fullname) {
		return bookingRepo.findBookingByFullName(fullname);
	}

	@Override
	@Transactional
	public Optional<Booking> deleteBooking(Integer id) {
		try {
	        Optional<Booking> bookingOpt = bookingRepo.findById(id);
	        if (bookingOpt.isPresent()) {
	        	Booking booking = bookingOpt.get();
	        	bookingRepo.deleteById(id);
	            return Optional.of(booking); 
	        } else {
	            System.out.println("Booking with ID " + id + " not found.");
	            return Optional.empty();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi xóa đặt vé.", e);
	    }
	}
}
