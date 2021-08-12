package edu.miu.cs.cs544.ea_ars.controller;


import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.domain.User;
import edu.miu.cs.cs544.ea_ars.domain.UserPrincipal;
import edu.miu.cs.cs544.ea_ars.dto.PassengerAdopter;
import edu.miu.cs.cs544.ea_ars.dto.PassengerDTO;
import edu.miu.cs.cs544.ea_ars.dto.ReservationAdopter;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.exception.CustomErrorType;

import edu.miu.cs.cs544.ea_ars.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/passengers/{id}")
    public ResponseEntity<?> getOnePassenger(@PathVariable Long id) {

//        Check if current user role is passaenger and searching for his own reservation
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       UserDetails name  = userDetailsService.loadUserByUsername(authentication.getName());

       PassengerDTO passenger = passengerService.findOne(id);
        List<ReservationDTO> reservationDTOS = passenger.getReservations();
        for(ReservationDTO res : reservationDTOS){
            if(res.getReservedBy().equals(name.getUsername())){
                return new ResponseEntity<PassengerDTO>(passenger, HttpStatus.OK);
            }
        }
           if (passenger == null) {
               return new ResponseEntity<CustomErrorType>(new CustomErrorType("Passenger with id = " + id + " not found"), HttpStatus.NOT_FOUND);
           }
        return null;
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

            for (Reservation reservation : passengerDTO.getReservations()) {
                reservation.setPassenger(PassengerAdopter.getPassenger(passengerDTO));
//                passengerDTO.addReservation(reservation);
//                reservation.setPassenger(PassengerAdopter.getPassenger(passengerDTO));
            }
            return passengerService.addPassenger(passengerDTO);
        } else {
            return passengerService.addPassenger(passengerDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        PassengerDTO passenger = passengerService.findOne(id);
        if (passenger == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Passenger with id = " + id + " not found"), HttpStatus.NOT_FOUND);
        } else {
            passengerService.deletePassenger(id);
            return new ResponseEntity<Object>("Passenger successfully deleted", HttpStatus.OK);
        }
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
        List<Reservation> reservations = passenger.getReservations();
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<?> MakeReservation(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        Passenger passenger = PassengerAdopter.getPassenger(passengerService.findOne(id));
        // reservation.setPassenger(passenger);
//        for (Reservation reservation : passengerDTO.getReservations()) {
//            reservation.setPassenger(PassengerAdopter.getPassenger(passengerDTO));
        passenger.addReservation(ReservationAdopter.getReservation(reservation));
        System.out.println(reservation);
        System.out.println(passenger);
        PassengerDTO passengerDTO = PassengerAdopter.getPassengerDTO(passenger);
//        Passenger passenger2 = passengerService.findOne(id);
//        passengerService.flush();
        passengerService.addPassenger(passengerDTO);
        System.out.println(passenger);
//        reservationService.addReservation(reservation);
//        passengerService.updatePassenger(id, passengerDTO);
        return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations/{reservationId}")
    public ResponseEntity<?> getOneReservation(@PathVariable Long id, @PathVariable Long reservationId) {
        PassengerDTO passenger = passengerService.findOne(id);

        for (Reservation reservation : passenger.getReservations()) {
            if (reservation.getId() == reservationId)
                return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        }
//        ReservationDTO reservation = reservationService.findOne(reservationId);
        return new ResponseEntity<CustomErrorType>(new CustomErrorType("Reservation not found"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id, @PathVariable Long reservationId) {
        PassengerDTO passenger = passengerService.findOne(id);
        if (passenger.getReservations().size() > 0) {
            for (Reservation reservation : passenger.getReservations()) {
                if (reservation.getId() == reservationId) {
                    reservationService.deleteReservation(reservationId);
                    return new ResponseEntity<Object>("Reservation canceled", HttpStatus.OK);
                }

            }
        }
        return new ResponseEntity<Object>("Reservation not found", HttpStatus.OK);
    }
}
