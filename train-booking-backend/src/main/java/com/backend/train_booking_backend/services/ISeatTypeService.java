package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.SeatType;

public interface ISeatTypeService {
	List<SeatType> getAllSeatTypes();

	SeatType getSeatType(Integer id);

	SeatType addSeatType(SeatType seattype);

	SeatType updateSeatType(Integer id, SeatType seattype);

	SeatType getSeatTypeBySeatTypename(String seattypename);

	List<SeatType> deleteSeatType(Integer[] ids);
}
