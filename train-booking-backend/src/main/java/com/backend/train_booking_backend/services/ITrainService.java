package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Train;

public interface ITrainService {
	List<Train> getAllTrains();

	Train getTrain(Integer id);

	Train addTrain(Train train);

	Train updateTrain(Integer id, Train train);

	Train getTrainByTrainname(String trainname);

	Optional<Train> deleteTrain(Integer id);
}
