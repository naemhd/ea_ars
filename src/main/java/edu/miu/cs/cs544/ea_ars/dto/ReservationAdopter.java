package edu.miu.cs.cs544.ea_ars.dto;

import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationAdopter {

    public static Reservation getReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        if (reservationDTO != null) {
            reservation.setReservationCode(reservationDTO.getReservationCode());
            reservation.setReservationDate( reservationDTO.getReservationDate());
            reservation.setId(reservationDTO.getId());
            reservation.setReservedBy(reservationDTO.getReservedBy());
            reservation.setPassenger(reservationDTO.getPassenger());
            reservation.setTickets(reservationDTO.getTickets());
        }
        return reservation;
    }

    public static ReservationDTO getReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        if (reservation != null) {
            reservationDTO.setReservationCode(reservation.getReservationCode());
            reservationDTO.setReservationDate( reservation.getReservationDate());
            reservationDTO.setId(reservation.getId());
            reservationDTO.setReservedBy(reservation.getReservedBy());
            reservationDTO.setPassenger(reservation.getPassenger());
            reservationDTO.setTickets(reservation.getTickets());
        }
        return reservationDTO;
    }

    public static List<ReservationDTO> getReservationDTOList(List<Reservation> reservationList) {
       return reservationList.stream().map(r->getReservationDTO(r)).collect(Collectors.toList());

    }
    public static List<Reservation> getReservationList(List<ReservationDTO> reservationDTOList) {

        return reservationDTOList.stream().map(r->getReservation(r)).collect(Collectors.toList());
    }
}
