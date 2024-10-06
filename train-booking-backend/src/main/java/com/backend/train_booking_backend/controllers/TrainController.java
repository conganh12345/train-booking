package com.backend.train_booking_backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.train_booking_backend.exception.TrainValidate;
import com.backend.train_booking_backend.models.Train;
import com.backend.train_booking_backend.services.ITrainService;

@RestController
@Validated
@RequestMapping("/api/train")
public class TrainController {
	@Autowired
	private ITrainService trainService;
	private TrainValidate validation = new TrainValidate();

	// Exception to return error json
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<Train>> getAllTrain() {
		List<Train> trains = trainService.getAllTrains();
		if (trains.isEmpty()) {
			trains = new ArrayList<Train>();
		}
		return new ResponseEntity<>(trains, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Train> getTrain(@PathVariable Integer id) {
		Train train = trainService.getTrain(id);
		if (train == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(train, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Train> addTrain(@RequestBody Train train) {
		validation.validate(train);
		Train createdTrain = trainService.addTrain(train);
		return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train, @PathVariable Integer id) {
		validation.validate(train);
		Train updatedTrain = trainService.updateTrain(id, train);
		if (updatedTrain == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
	}

	@GetMapping("/{trainname}")
	public ResponseEntity<Train> getTrainbyTrainname(@PathVariable String trainname) {
		Train train = trainService.getTrainByTrainname(trainname);
		if (train == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(train, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Train> deleteTrain(@PathVariable Integer id) {
	    if (trainService.deleteTrain(id)) {
	    	return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
