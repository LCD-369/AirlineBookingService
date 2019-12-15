package com.team.alpha.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;
import com.team.alpha.model.User;
import com.team.alpha.service.TicketService;

@RestController
@CrossOrigin
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
	
	
	@GetMapping(value="/ticket/{flightNumber}", produces={"application/json", "application/xml"})
	public ResponseEntity<List<Ticket>> findTicket(@PathVariable(value="flightNumber") int flightNumber) {
		List<Ticket> ticket = ticketService.findByFlightNumber(flightNumber);
		if (ticket.isEmpty()) {
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(ticket, HttpStatus.OK);
	}
	
	@GetMapping(value="/ticket/find", produces={"application/json", "application/xml"})
	public ResponseEntity<List<Ticket>> findUserTickets(@RequestParam(value="userid") int id) {
		List<Ticket> ticket = ticketService.findByUserId(id);
		if (ticket.isEmpty()) {
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(ticket, HttpStatus.OK);	
	}
	

	@PostMapping(value="/ticket/reserve/{flight}/{row}/{seat}", produces={"application/json", "application/xml"}, consumes={"application/json", "application/xml"})
	public ResponseEntity<Ticket> bookTicket(@PathVariable final int flight,
			@PathVariable final int row, @PathVariable final String seat,
			@RequestBody final User user) {
			return new ResponseEntity<>(ticketService.bookTicket(
					new SeatAspects(ticketService.getFlight(flight), row, seat), user),
					HttpStatus.CREATED);
		
	}
	
	@PutMapping(value="/ticket/cancel", produces={"application/json", "application/xml"}, consumes={"application/json", "application/xml"})
	public ResponseEntity<Ticket> bookTicket(@RequestBody final Ticket ticket) {
			return new ResponseEntity<Ticket>(ticketService.cancelExistingBooking(ticket),
					HttpStatus.CREATED);
	}
	
}
