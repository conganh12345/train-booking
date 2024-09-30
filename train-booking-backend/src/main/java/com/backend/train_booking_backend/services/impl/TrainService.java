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
import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.repositories.TrainRepository;
import com.backend.train_booking_backend.services.ITrainService;

@Service
public class TrainService implements ITrainService {

	@Autowired
	private TrainRepository trainRepo;

	@Override
	public List<Train> getAllTrains() {
		return trainRepo.findAll();
	}

	@Override
	public Train getTrain(Integer id) {
		return trainRepo.findById(id).get();
	}

	@Override
	public Train addTrain(Train train) {
		try {
			return trainRepo.save(train);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm tàu.", e);
		}
	}

	@Override
	public Train updateTrain(Integer id, Train train) {
		try {
			Optional<Train> oldTrainOpt = trainRepo.findById(id);
			if (oldTrainOpt.isPresent()) {
				train.setId(id);
			}
			return trainRepo.save(train);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa tàu.", e);
		}
	}

	@Override
	public Train getTrainByTrainname(String trainname) {
		return trainRepo.findTrainByTrainname(trainname);
	}

	@Override
	public List<Train> deleteTrain(Integer[] ids) {
		List<Train> trainDeletes = new ArrayList<>();

		try {
			for (Integer id : ids) {
				Optional<Train> trainOpt = trainRepo.findById(id);
				if (trainOpt.isPresent()) {
					Train trainname = trainOpt.get();
					trainDeletes.add(trainname);
					trainRepo.deleteById(id);
				} else {
					System.out.println("Train with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa tàu.", e);
		}
		return trainDeletes;
	}

}
