package edu.miu.cs.cs544.ea_ars.service;
import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.AirPortDto;
import edu.miu.cs.cs544.ea_ars.dto.adapter.AirPortDtoEntity;
import edu.miu.cs.cs544.ea_ars.repository.AirPortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirPortServiceImpl implements AirPortService {
    @Autowired
    AirPortRepository airPortRepository;
    @Autowired
    AirPortDtoEntity airPortDtoEntity;

    @Override
    public Page<Airport> findAll(Pageable pageable) {
        return airPortRepository.findAll(pageable);
    }

    @Override
    public List<AirPortDto> findAllAirports() {

        return airPortDtoEntity.entityToDto(airPortRepository.findAll());
    }

    @Override
    public AirPortDto findAirPortById(Long id) {
        System.out.println("In service class");
        return airPortDtoEntity.entityToDto(airPortRepository.findById(id).get());
    }

    @Override
    public Airport saveOrUpdateAirport(AirPortDto airPortDto) {
        Airport airport=airPortDtoEntity.creatEntityFromDto(airPortDto);
       return  airport=airPortRepository.save(airport);
    }

    @Override
    public Airport updateAirport(AirPortDto airPortDto) {
        Airport airport=airPortDtoEntity.dtoToEntity(airPortDto);
        return  airport=airPortRepository.save(airport);
    }

    @Override
    public void deleteAirPort(Long id) {
         airPortRepository.deleteById(id);
    }
}
