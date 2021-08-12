package edu.miu.cs.cs544.ea_ars.dto.adapter;
import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.AirPortDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirPortDtoEntity {
     public AirPortDto entityToDto(Airport airport){
         AirPortDto dto=new AirPortDto();
         dto.setId(airport.getId());
         dto.setAirportCode(airport.getAirportCode());
         dto.setName(airport.getName());
         dto.setAddress(airport.getAddress());
         return  dto;
     }
     public List<AirPortDto> entityToDto(List<Airport> airports){
         return airports.stream().map(a->entityToDto(a)).collect(Collectors.toList());
     }
     public Airport dtoToEntity(AirPortDto airPortDto){
          Airport  airport=new Airport();
          airport.setId(airPortDto.getId());
          airport.setAirportCode(airPortDto.getAirportCode());
          airport.setName(airPortDto.getName());
          airport.setAddress(airPortDto.getAddress());
          return airport;
     }
    public Airport creatEntityFromDto(AirPortDto airPortDto){
        Airport  airport=new Airport();
        airport.setAirportCode(airPortDto.getAirportCode());
        airport.setName(airPortDto.getName());
        airport.setAddress(airPortDto.getAddress());
        return airport;
    }
}
