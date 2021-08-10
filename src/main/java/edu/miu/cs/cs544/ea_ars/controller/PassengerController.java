package edu.miu.cs.cs544.ea_ars.controller;


import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.PassengerAdopter;
import edu.miu.cs.cs544.ea_ars.dto.PassengerDTO;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.exception.CustomErrorType;

import edu.miu.cs.cs544.ea_ars.service.PassengerService;
import edu.miu.cs.cs544.ea_ars.service.PassengerServiceImpl;
import edu.miu.cs.cs544.ea_ars.service.ReservationService;
import edu.miu.cs.cs544.ea_ars.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    private ReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePassenger(@PathVariable Long id) {

        PassengerDTO passenger = passengerService.findOne(id);
        if (passenger == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Passenger with id = " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PassengerDTO>(passenger, HttpStatus.OK);
    }

    @GetMapping(params = "paged=true")
    public Page<Passenger> findAll(Pageable pageable) {
        return passengerService.findAll(pageable);
    }

    @GetMapping
    public List<PassengerDTO> getAllPassenger() {
        return passengerService.findAll();
    }

    @PostMapping()
    public PassengerDTO AddPassenger(@Valid @RequestBody PassengerDTO passengerDTO) {
        if (passengerDTO.getReservations().size() > 0) {
            for (ReservationDTO reservationDTO : passengerDTO.getReservations()) {
                reservationDTO.setPassenger(PassengerAdopter.getPassenger(passengerDTO));
            }
        }
        return passengerService.addPassenger(passengerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        PassengerDTO passenger = passengerService.findOne(id);
        if (passenger == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Passenger with id = " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        passengerService.deletePassenger(id);
        return new ResponseEntity<Object>("Passenger successfully deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable Long id, @Valid @RequestBody PassengerDTO passenger) {
        PassengerDTO passengerToBeUpdated = passengerService.findOne(id);

        if (passengerToBeUpdated == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Passenger with id = " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PassengerDTO>(passengerService.updatePassenger(id, passenger), HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<?> getReservation(@PathVariable Long id) {
        PassengerDTO passenger = passengerService.findOne(id);
        List<ReservationDTO> reservations = passenger.getReservations();
        return new ResponseEntity<List<ReservationDTO>>(reservations, HttpStatus.OK);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<?> MakeReservation(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        PassengerDTO passenger = passengerService.findOne(id);
//        reservation.setPassenger(passenger);
        passenger.addReservation(reservation);
//        Passenger passenger2 = passengerService.findOne(id);
//        passengerService.flush();
//        passengerService.addPassenger(passenger);
//        reservationService.addReservation(reservation);
//        passengerService.updatePassenger(id,passenger);
        return new ResponseEntity<PassengerDTO>(passengerService.updatePassenger(id, passenger), HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable Long id, @PathVariable Long reservationId) {
        PassengerDTO passenger = passengerService.findOne(id);
        ReservationDTO reservation = reservationService.findOne(reservationId);
        return new ResponseEntity<ReservationDTO>(reservation, HttpStatus.OK);
    }
}