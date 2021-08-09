package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> getAllFlight(){
        return flightRepository.findAll();
    }

    @Override
    public Page<Flight> getAllFlightPage(Pageable pageable){
        return flightRepository.findAll(pageable);
    }

    @Override
    public boolean addFlight(Flight flight){
        if(flightRepository.existsByFlightNumber(flight.getFlightNumber())){
            return false;
        }
        else{
            flightRepository.save(flight);
        }
        return true;
    }

    @Override
    public Flight updateFlight(Flight flight) {
        Flight flight1 = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if(flight1 != null){
            flight1.setAirline(flight.getAirline());
            flight1.setDestination(flight.getDestination());
            flight1.setOrigin(flight.getOrigin());

            flight1.setArrivalDate(flight.getArrivalDate());
            flight1.setDepartureDate(flight.getDepartureDate());
            flight1.setArrivalTime(flight.getArrivalTime());
            flight1.setCapacity(flight.getCapacity());

            flightRepository.save(flight1);
        }
        return flight1;
    }

    @Override
    public boolean existsByFlightNumber(String flightNumber){
        return flightRepository.existsByFlightNumber(flightNumber);
    }

    @Override
    public Flight getFlight(String flightNumber){
        if(flightRepository.existsByFlightNumber(flightNumber)){
            return flightRepository.findByFlightNumber(flightNumber);
        }
        return new Flight();
    }
}
