package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.exception.StationValidate;
import com.backend.train_booking_backend.models.Station;
import com.backend.train_booking_backend.models.User;
import com.backend.train_booking_backend.repositories.StationRepository;
import com.backend.train_booking_backend.services.IStationService;

import jakarta.validation.ValidationException;

@Service
public class StationService implements IStationService {
	@Autowired
	private StationRepository stationRepo;

	@Override
	public List<Station> getAllStations() {
		return stationRepo.findAll();
	}

	@Override
	public Station getStation(Integer id) {
		return stationRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Station addStation(Station station) {
		try {
			return stationRepo.save(station);

		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm nhà ga.", e);
		}
	}

	@Override
	public Station updateStation(Integer id, Station station) {
		try {
			Optional<Station> oldStationOpt = stationRepo.findById(id);
			if (oldStationOpt.isPresent()) {
				station.setId(id);
			}
			return stationRepo.save(station);
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi sửa nhà ga.", e);
		}
	}

	@Override
	public Station getStationByStationname(String stationname) {
		return stationRepo.findStationByStationname(stationname);
	}

	@Override
	public List<Station> deleteStation(Integer[] ids) {
		List<Station> stationDeletes = new ArrayList<>();

		try {
			for (Integer id : ids) {
				Optional<Station> stationOpt = stationRepo.findById(id);
				if (stationOpt.isPresent()) {
					Station station = stationOpt.get();
					stationDeletes.add(station);
					stationRepo.deleteById(id);
				} else {
					System.out.println("Station with ID " + id + " not found.");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi xóa nhà ga.", e);
		}
		return stationDeletes;
	}

}
