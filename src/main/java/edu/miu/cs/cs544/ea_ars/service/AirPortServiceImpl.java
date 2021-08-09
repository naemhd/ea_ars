package edu.miu.cs.cs544.ea_ars.service;
;
import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.repository.AirPortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirPortServiceImpl implements AirPortService {
    @Autowired
    AirPortRepository airPortRepository;
    @Override
    public List<Airport> findAllAirports() {
        return airPortRepository.findAll();
    }

    @Override
    public Airport findAirPortById(Long id) {
        System.out.println("In service class");
        return airPortRepository.findById(id).get();
    }

    @Override
    public Airport saveOrUpdateAirport(Airport airport) {
        return airPortRepository.save(airport);
    }

    @Override
    public void deleteAirPort(Long id) {
         airPortRepository.deleteById(id);
    }
}
