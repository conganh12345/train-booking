package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Station;

public interface IStationService {
	List<Station> getAllStations();

	Station getStation(Integer id);

	Station addStation(Station station);

	Station updateStation(Integer id, Station station);

	Station getStationByStationname(String stationname);

	boolean deleteStation(Integer id);
}
