package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.Station;
import com.backend.train_booking_backend.models.User;

public interface IStationService 
{
	List<Station> getAllStations();
	
	Station getStation(Integer id);
	
	Station addStation(Station station);
	
	Station updateStation(Integer id, Station station);
	
	Station getStationByStationname(String stationname);
	
    List<Station> deleteStation(Integer[] ids);
}
