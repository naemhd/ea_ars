package edu.miu.cs.cs544.ea_ars.repository;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    public Flight findByFlightNumber(String flightnumber);

    public boolean existsByFlightNumber(String flightNumber);


@Query("SELECT f FROM Flight f join f.origin o join f.destination d " +
        "WHERE d.airportCode = :dPort AND f.departureDate = :flightDate AND o.airportCode = :oPort")
List<Flight> findAllDirectFlightDate(@Param("oPort") String origport,
                                     @Param("dPort") String depport,
                                     @Param("flightDate") LocalDate flightDate);
}
