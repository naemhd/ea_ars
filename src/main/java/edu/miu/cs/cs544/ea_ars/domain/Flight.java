package edu.miu.cs.cs544.ea_ars.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@ToString(exclude = {"airline","origin","destination","tickets"})
@EqualsAndHashCode(exclude = {"airline","origin","destination","tickets"})
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String flightNumber;

	private int capacity;
	
	private LocalDate departureDate;

	private LocalTime departureTime;

	private LocalDate arrivalDate;

	private LocalTime arrivalTime;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airline airline;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport origin;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Airport destination;

	@OneToMany(mappedBy = "flight")
	private Set<Ticket> tickets = new HashSet<>();

	public Flight(String flightnr, LocalDate departureDate, LocalTime departureTime,
				  LocalDate arrivalDate, LocalTime arrivalTime) {
		this.flightNumber = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
//		numberOfPaidReservation++;
	}

	public Flight(String flightnr, LocalDate departureDate, LocalTime departureTime,
                  LocalDate arrivalDate, LocalTime arrivalTime, Airline airline,
                  Airport origin, Airport destination) {
		this.flightNumber = flightnr;
		setDepartureDate(departureDate);
		setDepartureTime(departureTime);
		setArrivalDate(arrivalDate);
		setArrivalTime(arrivalTime);
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
	}

//	private void setNumberOfPaidReservation(){}

//	Collections convenience method
	public boolean addTickets(Ticket ticket){
		boolean success=false;
		if(tickets.add(ticket)){
			ticket.setFlight(this);
			success=true;
		}
		return success;
	}
}
