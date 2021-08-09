package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flight")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @GetMapping
    public List<Flight> getAllFlights(){
        return flightService.getAllFlight();
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<?> getFlight(@PathVariable String flightNumber){
        try {
            return new ResponseEntity(flightService.getFlight(flightNumber),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flightRequestEntity){
        //security check
       try{
            if(flightService.addFlight(flightRequestEntity)){
                return  new ResponseEntity(HttpStatus.OK);
            }
            else{
                return  new ResponseEntity(HttpStatus.valueOf("Something went wrong!"));
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.valueOf(HttpStatus.BAD_REQUEST + e.getMessage() ));
        }
    }

    @PutMapping("/update/")
    public ResponseEntity<Flight> updateFlight(@PathVariable Flight flight){
        try{
            return new ResponseEntity(flightService.updateFlight(flight), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.valueOf(e.getMessage()));
        }
    }
}
