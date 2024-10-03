package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Booking;

public interface IBookingService {
	List<Booking> getAllBookings();

	Booking getBooking(Integer id);

	Booking addBooking(Booking booking);

	Booking updateBooking(Integer id, Booking booking);

	Booking getBookingByFullName(String fullname);

	Optional<Booking> deleteBooking(Integer id);
}