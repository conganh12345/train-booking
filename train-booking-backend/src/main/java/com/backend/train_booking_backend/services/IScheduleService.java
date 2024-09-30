package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.Schedule;

public interface IScheduleService {
	List<Schedule> getAllSchedules();

	Schedule getSchedule(Integer id);

	Schedule addSchedule(Schedule user);

	Schedule updateSchedule(Integer id, Schedule user);

	Schedule getScheduleByScheduleName(String schedulename);

	List<Schedule> deleteSchedule(Integer[] ids);
}
