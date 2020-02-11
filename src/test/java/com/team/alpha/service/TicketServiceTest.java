package com.team.alpha.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.team.alpha.model.Airport;
import com.team.alpha.model.Flight;
import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;
import com.team.alpha.model.User;
import com.team.alpha.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRep;
	
	@InjectMocks
	private TicketService ticketService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	} 

	@Test
	public void testFindAllTickets() throws Exception {
		List<Ticket> list = new ArrayList<Ticket>();
		
		when(ticketRep.findAll()).thenReturn(list);
		assertNotNull(list);
	}

	@Test
	@Ignore
	public void testGetTicket() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	@Ignore
	public void testGetBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testBookTicketSeatAspectsUser() throws Exception {
		Airport a = new Airport("0C1", "Airport Test", "Test City", "Texas", "United States");
		Airport a2 = new Airport("0C2", "Airport Test Two", "Test City Two", "Virginia", "United States");
		LocalDate arrive = LocalDate.of(2020, 12, 12);
		LocalDate depart = LocalDate.of(2020, 10, 12);
		Flight f = new Flight(123455, a, a2, depart, arrive, 123455);
		SeatAspects sa = new SeatAspects(f, 23, "A");
		String p = "testpassword";
		User u = new User("testDisplay", "testUsername", p, "test@email.com", "2102232232");
		Ticket t2 = new Ticket();
		Ticket t = new Ticket();
		when(ticketRep.saveAndFlush(any(Ticket.class))).thenReturn(t2);
		when(ticketRep.findById(sa)).thenReturn(Optional.of(t));
		LocalDateTime time = LocalDateTime.now().plusMinutes(10);
		ticketService.bookTicket(sa, u, time);
		assertNotNull(t2);	
	}

	@Test
	@Ignore
	public void testBookTicketSeatAspectsUserLocalDateTime() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	@Ignore
	public void testFindByFlightNumber() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	@Ignore
	public void testGetFlight() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	@Ignore
	public void testCancelExistingBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	@Ignore
	public void testFindByUserId() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
