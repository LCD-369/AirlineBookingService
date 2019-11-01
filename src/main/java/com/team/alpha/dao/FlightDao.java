package com.team.alpha.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight, Integer> {
	
	List<Flight> findByFlightNumber(int flightNumber);
}
