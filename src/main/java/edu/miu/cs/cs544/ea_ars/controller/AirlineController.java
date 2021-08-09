package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import edu.miu.cs.cs544.ea_ars.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<Airline> findAll(){
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline findById(@PathVariable(name = "id") Long airlineId) {
        return airlineService.findById(airlineId);
    }

//    @GetMapping(params = "paged=true")
//    public Page<Airline> findAll(Pageable pageable) {
//        return countryRepository.findAll(pageable);
//    }

    @PostMapping
    public Object createAirline(@Valid @RequestBody Airline airline, BindingResult result) {
        if (!result.hasErrors()) {
            return airlineService.save(airline);
        }else{
            return result.getAllErrors();
        }
    }

    @PutMapping("/{id}")
    public Airline updateAirline(@PathVariable(name = "id") Long airlineId,
                                 @Valid @RequestBody Airline airline) {
//        countryRepository.findById(countryId).orElseThrow(RuntimeException::new);
        return airlineService.save(airline);
    }

    @DeleteMapping("/{id}")
    public Airline deleteAirline(@PathVariable(name = "id") Long airlineId){
        Airline airline=airlineService.findById(airlineId);
        airlineService.delete(airline);
        return airline;
    }
}
