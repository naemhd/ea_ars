package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Airport;

import java.util.List;

public interface AirPortService {
    List<Airport> findAllAirports();

    Airport findAirPortById(Long id);

    Airport saveOrUpdateAirport(Airport airport);

    void deleteAirPort(Long id);

}
