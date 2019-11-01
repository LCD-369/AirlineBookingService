package com.team.alpha.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_flights")
public class Flight implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="departure")
	private Airport departureAirport;
	
	@ManyToOne
	@JoinColumn(name="destination")
	private Airport destination;
	
	@Column(name="departure_date")
	private LocalDateTime departureDate;
	
	@Column(name="arrival_date")
	private LocalDateTime arrivalDate;
	
	@Column(name="flight_number")
	private int flightNumber;
	
	public Flight() {
	}

	public Flight(int id, Airport departureAirport, Airport destination, LocalDateTime departureDate,
			LocalDateTime arrivalDate, int flightNumber) {
		this.id = id;
		this.departureAirport = departureAirport;
		this.destination = destination;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.flightNumber = flightNumber;
	}

	public int getId() {
		return id;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public Airport getDestination() {
		return destination;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public int getFlightNumber() {
		return flightNumber;
	}
	
	@Override
	public int hashCode() {
		return id;
		
		
	}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof Flight) {
				return id == ((Flight) obj).getId()
						&& Objects.equals(departureAirport, ((Flight) obj).getDepartureAirport())
						&& Objects.equals(departureDate, ((Flight) obj).getDepartureDate())
						&& Objects.equals(destination, ((Flight) obj).getDestination())
						&& Objects.equals(arrivalDate, ((Flight) obj).getArrivalDate());
			} else {
				return false;
			}
		}
		
		@Override
		public String toString() {
			return String.format(
					"Flight %d (internal #%d) from %s (%s) at %s %s to %s (%s) at %s %s",
					flightNumber, id, departureAirport.getCode(),
					departureAirport.getName(), departureDate.toLocalDate().toString(),
					departureDate.toLocalTime().toString(), destination.getCode(),
					destination.getName(), arrivalDate.toLocalDate().toString(),
					arrivalDate.toLocalTime().toString());
		}
	
}
