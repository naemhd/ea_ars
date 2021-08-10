package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import edu.miu.cs.cs544.ea_ars.dto.adapter.AirPortDtoEntity;
import edu.miu.cs.cs544.ea_ars.repository.AirPortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.miu.cs.cs544.ea_ars.repository.AirlineRepository;

import java.util.List;

@Service
@Transactional
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines(){

        return airlineRepository.findAll();
    }

    public Airline findById(Long airlineId){
        return airlineRepository.findById(airlineId).get();
    }

    public Airline save(Airline airline){
        airlineRepository.save(airline);
        return airline;
    }
}
