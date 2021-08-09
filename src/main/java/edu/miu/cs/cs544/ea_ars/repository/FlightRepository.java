package edu.miu.cs.cs544.ea_ars.repository;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface FlightRepository extends JpaRepository<Flight, Integer> {

//    @Query("SELECT f FROM Flight  f join Airport a join Airport d WHERE f.flightNumber=?1")
    public Flight findByFlightNumber(String flightnumber);

    public boolean existsByFlightNumber(String flightNumber);

    public Flight findByFlightNumberAndCapacity(String flightnumber, int capacity);
}
