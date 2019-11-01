package com.team.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;
import com.team.alpha.model.User;
import com.team.alpha.service.TicketService;

@RestController
@RequestMapping(path="/book")
public class TicketController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private TicketService ticketService;
	
	
	@GetMapping(value="/ticket/", produces={"application/json", "application/xml"})
	public ResponseEntity<List<Ticket>> getAllTickets()	{
		List<Ticket> tickets = ticketService.findAllTickets();
		if (tickets.isEmpty()) {
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);	
	}
	
	
	@PostMapping(value="/flights/{flight}/rows/{row}/seats/{seat}", consumes={"application/json", "application/xml"}, produces={"application/json", "application/xml"})
	public ResponseEntity<Ticket> bookTicket(@PathVariable int flight, @PathVariable int row, @PathVariable String seat,
			@RequestBody User user) {
		try {
			return new ResponseEntity<>(ticketService.bookTicket(new SeatAspects(ticketService.getFlight(flight), row, seat), user), HttpStatus.CREATED);
			
		} catch (IllegalArgumentException except) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (DataIntegrityViolationException|InvalidDataAccessApiUsageException except) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception except) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}
