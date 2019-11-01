package com.team.alpha.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.Airport;

@Repository
public interface AirportDao extends JpaRepository<Airport, String> {

}
