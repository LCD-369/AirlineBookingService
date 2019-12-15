package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.AirportDao;
import com.team.alpha.model.Airport;

@Service
public class AirportService {

	@Autowired
	private AirportDao airportDao;
	
	public List<Airport> getAirports() {
		return airportDao.findAll();	
	}
	
	public Airport findAirportByName(String name) {
		List<Airport> list = airportDao.searchByName(name);
		if (list.isEmpty()) {
			return null;
		} else {
			Airport airport = list.get(0);
			return airport;
		}
	}
	
}
