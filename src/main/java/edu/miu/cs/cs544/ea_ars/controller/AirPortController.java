package edu.miu.cs.cs544.ea_ars.controller;


import edu.miu.cs.cs544.ea_ars.domain.Airport;
import edu.miu.cs.cs544.ea_ars.dto.AirPortDto;
import edu.miu.cs.cs544.ea_ars.dto.adapter.AirPortDtoEntity;
import edu.miu.cs.cs544.ea_ars.repository.AirPortRepository;
import edu.miu.cs.cs544.ea_ars.service.AirPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(params = "paged=true")
    public Page<Airport> findAll(Pageable pageable) {
        return airPortService.findAll(pageable);
    }
    @GetMapping()
    public List<AirPortDto> getAllAirports() {

        return airPortService.findAllAirports();
    }

    @GetMapping({"/{id}"})
    public AirPortDto getAirPortById(@PathVariable(name = "id") Long airportId) {
        return airPortService.findAirPortById(airportId);
    }
    @PostMapping
    public ResponseEntity<Airport> createAirPort(@RequestBody AirPortDto airPortDto) {
        Airport airport = new Airport();
        airport = airPortService.saveOrUpdateAirport(airPortDto);
        if (airport != null) {
            return new ResponseEntity<Airport>(airport, HttpStatus.OK);
        }
        return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public String deleteAirPort(@PathVariable Long id) {

        airPortService.deleteAirPort(id);
        return "successfully delete airport with id "+id;
    }

    @PutMapping("{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable(name = "id") Long id, @RequestBody AirPortDto airPortDto) {
        Airport airport = airPortService.updateAirport(airPortDto);
        if (airport != null) {
            return new ResponseEntity<Airport>(airport, HttpStatus.OK);
        }
        return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND);
    }

}

