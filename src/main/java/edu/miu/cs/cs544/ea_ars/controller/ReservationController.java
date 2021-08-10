package edu.miu.cs.cs544.ea_ars.controller;


import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.exception.CustomErrorType;
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

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDTO> getAllReservation() {
        return reservationService.findAll();
    }

    @GetMapping(params = "paged=true")
    public Page<Reservation> findAll(Pageable pageable) {
        return reservationService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneReservation(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.findOne(id);
        if (reservationService.findOne(id) == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("A reservation with id = " + id + "not found "), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ReservationDTO AddReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        return reservationService.addReservation(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.findOne(id);
        if (reservationDTO == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("A reservation with id = " + id + "not found "), HttpStatus.NOT_FOUND);
        }

        reservationService.deleteReservation(id);
        return new ResponseEntity<Object>("Reservation successfully deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO reservationToBeUpdated = reservationService.findOne(id);

        if (reservationToBeUpdated == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("A reservation with id = " + id + "not found "), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReservationDTO>(reservationService.updateReservation(id, reservationDTO), HttpStatus.OK);
    }
}
