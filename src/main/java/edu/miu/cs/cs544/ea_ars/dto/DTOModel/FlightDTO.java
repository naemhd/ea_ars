package edu.miu.cs.cs544.ea_ars.dto.DTOModel;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.domain.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class FlightDTO {
    private int id;
    private String flightNumber;
    private int capacity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private Airline airline;
    private Airport origin;
    private Airport destination;
//    private Set<Ticket> tickets;
}