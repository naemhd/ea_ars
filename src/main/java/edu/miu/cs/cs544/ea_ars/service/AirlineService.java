package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.miu.cs.cs544.ea_ars.repository.AirlineRepository;

import java.util.List;

@Service
@Transactional
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public Page<Airline> getAllAirlines(Pageable pageable){
        return airlineRepository.findAll(pageable);
    }

    public Page<Airline> getAllAirlinesByAirport(Pageable pageable,String airportCode){
        return airlineRepository.listAirlinesByAirport(airportCode,pageable);
    }

    public Airline findById(Long airlineId){
        return airlineRepository.findById(airlineId).get();
    }

    public Airline save(Airline airline){
        airlineRepository.save(airline);
        return airline;
    }

    public Airline delete(Airline airline){
        airlineRepository.delete(airline);
        return airline;
    }
}
