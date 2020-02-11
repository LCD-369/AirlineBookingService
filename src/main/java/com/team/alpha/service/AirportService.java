package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.model.Airport;
import com.team.alpha.repository.AirportRepository;

@Service
public class AirportService {

	@Autowired
	private AirportRepository airportDao;
	
	public List<Airport> getAirports() {
		return airportDao.getAll();	
	}
	
	public Airport findAirportByName(String name) {
		List<Airport> list = airportDao.searchByName(name);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
			
		}
	}
	
}
