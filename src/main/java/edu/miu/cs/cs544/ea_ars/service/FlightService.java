package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface FlightService {
    List<FlightDTO> findAllFlight();
    Page<Flight> findAllFlight(Pageable pageable);
    boolean existsByFlightNumber(String flightNumber);
    FlightDTO findFlight(String flightNumber);
    Flight findFlightByNumber(String flightNumber);

    boolean saveFlight(FlightDTO flight);
    void saveFlights(Set<FlightDTO> flights);

    FlightDTO updateFlight(FlightDTO flight);

    void deleteFlight(String flightNumber);
    
    void updateCapacity(String flightNumber, int capacity);

    List<FlightDTO> findDirectFlights(String from, String to, LocalDate flightDate);

}
