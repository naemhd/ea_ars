package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.service.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @GetMapping
    public ResponseEntity<?> findAllFlights() {
        List<FlightDTO> flight = flightService.findAllFlight();
        if (flight != null) {
            return ResponseEntity.ok().body(flight);
        } else {
            return ResponseEntity.badRequest().body("No flight");
        }
    }

    //    Get pageable list of flights
    @GetMapping(params = "paged=true")
    public Page<Flight> findAllFlights(Pageable pageable) {
        Page<Flight> flights = flightService.findAllFlight(pageable);
        if (flights != null) {
            return flightService.findAllFlight(pageable);
        } else {
            return null;
        }
    }

    //    Get single flight
    @GetMapping("/{flightNumber}")
    public ResponseEntity<?> findFlight(@PathVariable String flightNumber) {
        try {
            return ResponseEntity.ok().body(flightService.findFlight(flightNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    //    Save a flight
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveFlight(@RequestBody FlightDTO flightRequestEntity, BindingResult result) {
        if (!result.hasErrors() && flightService.saveFlight(flightRequestEntity)) {
            return ResponseEntity.ok().body("Successfully Saved!");
        } else {
            return ResponseEntity.badRequest().body("Data not saved " + result.toString());
        }
    }

    //    Save flights
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<?> saveFlights(@RequestBody Set<FlightDTO> flights, BindingResult result) {
        if (!result.hasErrors()) {
            flightService.saveFlights(flights);
            return ResponseEntity.ok().body("Successfully saved " + result.toString());
        }
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    //    Update single flight if exist or save if new object
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{flightNumber}")
    public ResponseEntity<?> updateFlight(@PathVariable String flightNumber,@RequestBody FlightDTO flight, BindingResult result) {
        if (!result.hasErrors()) {
            if(flightNumber.equals(flight.getFlightNumber())) {
                return ResponseEntity.ok().body(flightService.updateFlight(flight));
            }
            else{
                return ResponseEntity.ok().body("Sorry!, Flight number you are trying to edit is different!");
            }
        } else {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
    }

    //    Delete a flight looking by flight number
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{flightNumber}")
    public ResponseEntity<?> deleteFlight(@Valid @PathVariable String flightNumber) {
        try{
            flightService.deleteFlight(flightNumber);
            return ResponseEntity.ok().body("Successfully deleted!");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //   View list of all flights between a departure and destination on a given date
//   Direct flight only
    @GetMapping("/direct")
    ResponseEntity<?> findAllDirectFlight(String from, String to,
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate flightDate) {
        List<FlightDTO> flights = flightService.findDirectFlights(from, to, flightDate);
        return ResponseEntity.ok().body(flights);
    }
}