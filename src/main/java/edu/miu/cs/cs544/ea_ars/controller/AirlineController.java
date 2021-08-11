package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import edu.miu.cs.cs544.ea_ars.dto.AirlineAdaptar;
import edu.miu.cs.cs544.ea_ars.dto.AirlineDTO;
import edu.miu.cs.cs544.ea_ars.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/airlines")
public class AirlineController {

    @Autowired
    private AirlineAdaptar airlineAdaptar;

    @Autowired
    private AirlineService airlineService;

    @GetMapping(params = "paged=true")
    public Page<Airline> findAll(Pageable pageable){
        return airlineService.getAllAirlines(pageable);

    }

    @GetMapping("/by-airport")
    public Page<Airline> findAllByAirport(Pageable pageable,String airportCode){
        return airlineService.getAllAirlinesByAirport(pageable,airportCode);
    }

    @GetMapping("/{id}")
    public Airline findById(@PathVariable(name = "id") Long airlineId) {
        return airlineService.findById(airlineId);
    }

    @PostMapping
    public Object createAirline(@Valid @RequestBody Airline airline, BindingResult result) {
        if (!result.hasErrors()) {
            return airlineService.save(airline);
        }else{
            return result.getAllErrors();
        }
    }

    @PutMapping("/{id}")
    public Object updateAirline(@PathVariable(name = "id") Long airlineId,
                                 @Valid @RequestBody Airline airline, BindingResult result) {
        if (!result.hasErrors()) {
            airline.setId(airlineId);
            return airlineService.save(airline);
        }else{
            return result.getAllErrors();
        }
    }

    @DeleteMapping("/{id}")
    public Airline deleteAirline(@PathVariable(name = "id") Long airlineId){
        Airline airline=airlineService.findById(airlineId);
        airlineService.delete(airline);
        return airline;
    }
}
