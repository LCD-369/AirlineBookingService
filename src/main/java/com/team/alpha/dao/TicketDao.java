package com.team.alpha.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;



@Repository
public interface TicketDao extends JpaRepository<Ticket, SeatAspects> {
	
	@Query(value="from Ticket where flight=:flightNumber and seat_row=:seatRow and seat=:seat")
	Ticket findByCompositeKeys(@Param("flightNumber") int flightnumber, @Param("seatRow") int seatrow, 
			@Param("seat") String seat);
	
	List<Ticket> findByBookingId(String bookingId);
}
