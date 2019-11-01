package com.team.alpha.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.team.alpha.controller.TicketController;
import com.team.alpha.dao.FlightDao;
import com.team.alpha.dao.TicketDao;
import com.team.alpha.model.Flight;
import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;
import com.team.alpha.model.User;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private FlightDao flightDao;
	
	private final int defaultBookingExpiration=10;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);
	
	public List<Ticket> findAllTickets() {		
		return ticketDao.findAll();		
	}
	
	public Flight getFlight(int flightNumber) {
		List<Flight> list = flightDao.findByFlightNumber(flightNumber);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public Ticket getTicket(SeatAspects seat) {
		return ticketDao.findById(seat).get();
	}
	
	public Ticket getTicketAlt(SeatAspects seat) {
		return ticketDao.findByCompositeKeys(seat.getFlight().getFlightNumber(), seat.getRow(), seat.getSeat());
	}

	public Ticket getBooking(String bookingId) {
		List<Ticket> list = ticketDao.findByBookingId(bookingId);
		if (list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			throw new IllegalStateException("Uniqueness constraint violated");
		} else {
			return list.get(0);
		}
	}
	
	public Ticket bookTicket(SeatAspects seat, User user) {	
		return bookTicket(seat, user,
				LocalDateTime.now().plusMinutes(defaultBookingExpiration));
				
	}

	@Transactional
	public Ticket bookTicket(SeatAspects seat, User user, LocalDateTime timeout) {
		
		
		System.out.println(timeout.toString());
		System.out.println(seat.toString());
		System.out.println(user.toString());
		Ticket ticket = getTicket(seat);
		System.out.println(ticket.toString());
		System.out.println(ticket.getReserver());
		if (ticket.getReserver() != null) {
			throw new IllegalArgumentException("Ticket already reserved");
		}
		ticket.setReserver(user);
		ticket.setReservationTimeout(timeout);
		ticket.setBookingId(DigestUtils.md5DigestAsHex(String.format("%d %d %s %d", seat.getFlight().getFlightNumber(),
			seat.getRow(), seat.getSeat(), user.getId()).getBytes()));
		ticketDao.saveAndFlush(ticket);
		return ticket;
	}
	
}
