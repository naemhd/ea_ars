package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.AirPortDto;

import java.util.List;

public interface Ticketservice {
    List<AirPortDto> findAllAirports();

    AirPortDto findAirPortById(Long id);

    Airport saveOrUpdateAirport(AirPortDto airPortDto);
    Airport updateAirport(AirPortDto airPortDto);

    void deleteAirPort(Long id);

}
