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
        //security check
        try {
            if (!result.hasErrors() && flightService.saveFlight(flightRequestEntity)) {
                return ResponseEntity.ok().body("Successfully Saved!");
            } else {
                return ResponseEntity.ok().body("Data not saved " + result.toString());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
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
    @PutMapping
    public ResponseEntity<?> updateFlight(@RequestBody FlightDTO flight, BindingResult result) {
        try {
            if (!result.hasErrors()) {
                return ResponseEntity.ok().body(flightService.updateFlight(flight));
            } else {
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    //    Delete a flight looking by flight number
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{flightNumber}")
    public ResponseEntity<?> deleteFlight(@Valid @PathVariable String flightNumber) {
        try {
            flightService.deleteFlight(flightNumber);
            return ResponseEntity.ok().body("Successfully deleted!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//   View list of all flights between a departure and destination on a given date
//   Direct flight only
    @GetMapping("/direct")
    ResponseEntity<?> findAllDirectFlight(String from, String to, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate) {
        try {
            List<FlightDTO> flights = flightService.findDirectFlights(from, to, flightDate);
            return ResponseEntity.ok().body(flights);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}