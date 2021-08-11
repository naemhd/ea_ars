package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.AirPortDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirPortService {
    Page<Airport> findAll(Pageable pageable);
    List<AirPortDto> findAllAirports();

    AirPortDto findAirPortById(Long id);

    Airport saveOrUpdateAirport(AirPortDto airPortDto);
    Airport updateAirport(AirPortDto airPortDto);

    void deleteAirPort(Long id);

}
