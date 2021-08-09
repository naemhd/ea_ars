package edu.miu.cs.cs544.ea_ars.controller;


import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.AirPortDto;
import edu.miu.cs.cs544.ea_ars.dto.adapter.AirPortDtoEntity;
import edu.miu.cs.cs544.ea_ars.repository.AirPortRepository;
import edu.miu.cs.cs544.ea_ars.service.AirPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirPortController {

    @Autowired
    AirPortDtoEntity airPortDtoEntity;
    @Autowired
    AirPortRepository airPortRepository;
    @Autowired
    AirPortService airPortService;

    @GetMapping()
    public List<AirPortDto> getAllAirports(){

        return airPortDtoEntity.entityToDto(airPortService.findAllAirports());
    }
    @GetMapping({"/get/{id}"})
    public Airport getAirPortById(@PathVariable(name="id") Long airportId){
        System.out.println("In controller class");
        return airPortService.findAirPortById(airportId);
//        return airPortDtoEntity.entityToDto(airPortService.findAirPortById(airportId));
    }
//    @GetMapping("/{id}")
//    public Airport getAirPortById(@PathVariable Long airportId){
//        return airPortService.findAirPortById(airportId);
//    }

    @PostMapping("/save")
    public ResponseEntity<Airport> createAirPort(@RequestBody AirPortDto airPortDto){
        Airport airport=new Airport();
        airport.setName(airPortDto.getName());
        airport.setAirportCode(airPortDto.getAirportCode());
        airport.setAddress(airPortDto.getAddress());
        airport=airPortService.saveOrUpdateAirport(airport);
        if(airport!=null){
            return new ResponseEntity<Airport>(airport, HttpStatus.OK);
        }
        return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAirPort(@PathVariable Long id){
        airPortService.deleteAirPort(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable(name="id") Long id, @RequestBody Airport airport){
        airport= airPortService.saveOrUpdateAirport(airport);
        if(airport!=null){
            return new ResponseEntity<Airport>(airport, HttpStatus.OK);
        }
        return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
    }
//    @PutMapping("/update/{id}")
    public ResponseEntity<Airport> updateAirport(@RequestBody AirPortDto airPortDto){
        Airport airport1= airPortService.findAirPortById(airPortDto.getId());
//        Airport airport= airPortService.findAirPortById(id);
//        airport.setAirportCode(airport1.getAirportCode());
//        airport.setName(airport1.getName());
//        airport.setAddress(airport1.getAddress());
        Airport airport2=airPortDtoEntity.dtoToEntity(airPortDto);
        airport1= airPortService.saveOrUpdateAirport(airport2);
        if(airport1!=null){
            return new ResponseEntity<Airport>(airport1, HttpStatus.OK);
        }
        return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
    }


}
