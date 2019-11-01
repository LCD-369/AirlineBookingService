package com.team.alpha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.team.alpha.dao.TicketDao;

@SpringBootApplication
public class AirlineBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineBookingServiceApplication.class, args);		
	}
	
	
	

}
