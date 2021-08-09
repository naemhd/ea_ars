package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    public List<Flight> getAllFlight();
    public Page<Flight> getAllFlightPage(Pageable pageable);
    public boolean existsByFlightNumber(String flightNumber);
    public Flight getFlight(String flightNumber);

    public boolean addFlight(Flight flight);
    public Flight updateFlight(Flight flight);

}
