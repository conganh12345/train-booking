package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.Schedule;
import com.backend.train_booking_backend.repositories.ScheduleRepository;
import com.backend.train_booking_backend.services.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;

	@Override
	public List<Schedule> getAllSchedules() {
		return scheduleRepo.findAll();
	}

	@Override
	public Schedule getSchedule(Integer id) {
		return scheduleRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Schedule addSchedule(Schedule schedule) {
		try {
			schedule.setDepartureDate(LocalDateTime.now());
			if (schedule.getEstimateArrivalDate() == null) {
				schedule.setEstimateArrivalDate(schedule.getDepartureDate().plusHours(5));
			}

			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm lịch trình.", e);
		}
	}

	@Override
	@Transactional
	public Schedule updateSchedule(Integer id, Schedule schedule) {
		try {
			Optional<Schedule> oldScheduleOpt = scheduleRepo.findById(id);
			if (oldScheduleOpt.isPresent()) {
				schedule.setId(id);
				schedule.setDepartureDate(null);
				schedule.setDepartureDate(oldScheduleOpt.get().getDepartureDate());
				schedule.setEstimateArrivalDate(LocalDateTime.now());
			}
			return scheduleRepo.save(schedule);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa lịch trình.", e);
		}
	}

	@Override
	public Schedule getScheduleByScheduleName(String schedulename) {
		return scheduleRepo.findScheduleByScheduleName(schedulename);
	}

	@Override
	@Transactional
	public List<Schedule> deleteSchedule(Integer[] ids) {
		List<Schedule> scheduleDeletes = new ArrayList<>();

		try {
			for (Integer id : ids) {
				Optional<Schedule> scheduleOpt = scheduleRepo.findById(id);
				if (scheduleOpt.isPresent()) {
					Schedule schedule = scheduleOpt.get();
					scheduleDeletes.add(schedule);
					scheduleRepo.deleteById(id);
				} else {
					System.out.println("Schdule with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa lịch trình.", e);
		}
		return scheduleDeletes;
	}
}
