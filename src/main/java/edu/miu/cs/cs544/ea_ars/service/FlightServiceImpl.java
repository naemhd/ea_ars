package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.dto.adapter.DTOAdapter;
import edu.miu.cs.cs544.ea_ars.dto.adapter.FlightDTOAdapter;
import edu.miu.cs.cs544.ea_ars.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightDTOAdapter flightDTOAdapter;

    @Autowired
    private DTOAdapter dtoAdapter;

    @Override
    public List<FlightDTO> findAllFlight(){
        return flightDTOAdapter.flightsDTOAdapter((flightRepository.findAll()));
    }

    @Override
    public Page<Flight> findAllFlight(Pageable pageable){
        Page<Flight> pageFlight =flightRepository.findAll(pageable);
        return pageFlight;
    }

    @Override
    public boolean saveFlight(FlightDTO flight){
        if(flightRepository.existsByFlightNumber(flight.getFlightNumber())){
            return false;
        }
        else{
            flightRepository.save(FlightDTOAdapter.dtoToFlight(flight));
        }
        return true;
    }

    @Override
    public void saveFlights(Set<FlightDTO> flights){
        flights.forEach(flight -> saveFlight(flight));
    }
    
    public void updateCapacity(String fn,int capacity) {
    	Flight flight1 = flightRepository.findByFlightNumber(fn);
    	flight1.setCapacity(capacity);
    	 flightRepository.save(flight1);
    	
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flight) {
        Flight flight1 = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if(flight1 != null){
            flight1.setAirline(flight.getAirline());
            flight1.setDestination(flight.getDestination());
            flight1.setOrigin(flight.getOrigin());

            flight1.setArrivalDate(flight.getArrivalDate());
            flight1.setDepartureDate(flight.getDepartureDate());
            flight1.setArrivalTime(flight.getArrivalTime());
            flight1.setCapacity(flight.getCapacity());

//            Flight flight2 = FlightDTOAdapter.dtoToFlight(flight1);
            flightRepository.save(flight1);
        }
        return FlightDTOAdapter.flightDTOAdapter(flight1);
    }

    @Override
    public void deleteFlight(String flightNumber) {
       FlightDTO flight = findFlight(flightNumber);
       if(flight!=null){
           flightRepository.delete(FlightDTOAdapter.dtoToFlight(flight));
       }
    }

    @Override
    public boolean existsByFlightNumber(String flightNumber){
        return flightRepository.existsByFlightNumber(flightNumber);
    }

    @Override
    public FlightDTO findFlight(String flightNumber){
        if(flightRepository.existsByFlightNumber(flightNumber)){
            return FlightDTOAdapter.flightDTOAdapter(flightRepository.findByFlightNumber(flightNumber));
        }
        return new FlightDTO();
    }
    
    @Override
    public Flight findFlightByNumber(String flightNumber){
    	return flightRepository.findByFlightNumber(flightNumber);
    }

    @Override
    public List<FlightDTO> findDirectFlights(String from, String to, LocalDate flightDate) {
        return FlightDTOAdapter.flightsDTOAdapter(flightRepository.findAllDirectFlightDate(from,to,flightDate));
    }
}
