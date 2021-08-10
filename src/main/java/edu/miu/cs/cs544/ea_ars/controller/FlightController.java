package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.dto.adapter.FlightDTOAdapter;
import edu.miu.cs.cs544.ea_ars.service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightDTOAdapter flightDTOAdapter;

    @Autowired
    private FlightServiceImpl flightService;

    @GetMapping
    public List<FlightDTO> findAllFlights() {
        return flightService.findAllFlight();
    }

//    Get pageable list of flights
    @GetMapping(params = "paged=true")
    public Page<Flight> findAllFlights(Pageable pageable) {
        return flightService.findAllFlight(pageable);
    }

    //    Get single flight
    @GetMapping("/{flightNumber}")
    public Object findFlight(@PathVariable String flightNumber) {
        try {
            return flightService.findFlight(flightNumber);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

//    Save a flight
    @PostMapping
    public Object saveFlight(@RequestBody FlightDTO flightRequestEntity, BindingResult result) {
        //security check
        try {
            if (!result.hasErrors() && flightService.saveFlight(flightRequestEntity)) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return result.toString();
            }
        } catch (Exception e) {
            return result.getAllErrors();
        }
    }

//    Save flights
    @PostMapping("/all")
    public Object saveFlights(@RequestBody Set<FlightDTO> flights, BindingResult result){
        if(!result.hasErrors()){
            flightService.saveFlights(flights);
            return result.toString();
        }
        return result.getAllErrors();
    }

//    Update single flight if exist or save if new object
    @PutMapping
    public Object updateFlight(@RequestBody FlightDTO flight, BindingResult result) {
        try {
            if (!result.hasErrors()) {
                return flightService.updateFlight(flight);
            } else {
                return result.getAllErrors();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    Delete a flight looking by flight number
    @DeleteMapping("{flightNumber}")
    public Object deleteFlight(@Valid @PathVariable String flightNumber) {
        try {
            flightService.deleteFlight(flightNumber);
            return "Successfully deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

//    View list of all flights between a departure and destination on a given date
//    Direct flight only
    @GetMapping("/direct")
        public @ResponseBody
    List<FlightDTO> findAllDirectFlight(String from, String to, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate){
        try {
            List<FlightDTO> flights= flightService.findDirectFlights(from, to, flightDate);
            return flights;
        }
        catch (Exception e){
            return new ArrayList<>();
        }

    }
}