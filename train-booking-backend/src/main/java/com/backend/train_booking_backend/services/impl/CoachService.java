package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.exception.TrainValidate;
import com.backend.train_booking_backend.models.Coach;
import com.backend.train_booking_backend.repositories.CoachRepository;
import com.backend.train_booking_backend.services.ICoachService;

@Service
public class CoachService implements ICoachService {

	@Autowired
	private CoachRepository coachRepo;

	@Override
	public List<Coach> getAllCoachs() {
		return coachRepo.findAll();
	}

	@Override
	public Coach getCoach(Integer id) {
		return coachRepo.findById(id).get();
	}

	@Override
	public Coach addCoach(Coach coach) {
		try {
			return coachRepo.save(coach);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm toa.", e);
		}
	}

	@Override
	public Coach updateCoach(Integer id, Coach coach) {
		try {
			Optional<Coach> oldCoachOpt = coachRepo.findById(id);
			if (oldCoachOpt.isPresent()) {
				coach.setId(id);
			}
			return coachRepo.save(coach);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa toa.", e);
		}
	}

	@Override
	public Coach getCoachByCoachName(String coachname) {
		return coachRepo.findCoachByCoachName(coachname);
	}

	@Override
	public List<Coach> deleteCoach(Integer[] ids) {
		List<Coach> coachDeletes = new ArrayList<>();

		try {
			for (Integer id : ids) {
				Optional<Coach> coachOpt = coachRepo.findById(id);
				if (coachOpt.isPresent()) {
					Coach coach = coachOpt.get();
					coachDeletes.add(coach);
					coachRepo.deleteById(id);
				} else {
					System.out.println("Coach with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa tàu.", e);
		}
		return coachDeletes;
	}
}
