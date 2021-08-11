package edu.miu.cs.cs544.ea_ars.dto;

import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdopter {

    public static Reservation getReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        if (reservationDTO != null) {
            reservation = new Reservation(reservationDTO.getReservationCode(), reservationDTO.getReservationDate(), reservationDTO.getReservedBy());
        }
        return reservation;
    }

    public static ReservationDTO getReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        if (reservation != null) {
            reservationDTO = new ReservationDTO(reservation.getReservationCode(), reservation.getReservationDate(), reservation.getReservedBy());
        }
        return reservationDTO;
    }

    public static List<ReservationDTO> getReservationDTOList(List<Reservation> reservationList) {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        if (reservationList.size() != 0) {
            for (Reservation reservation : reservationList) {
                reservationDTOList.add(ReservationAdopter.getReservationDTO(reservation));
            }
        }
        return reservationDTOList;
    }
    public static List<Reservation> getReservationList(List<ReservationDTO> reservationDTOList) {
        List<Reservation> reservationList = new ArrayList<>();
        if (reservationDTOList.size() != 0) {
            for (ReservationDTO reservation : reservationDTOList) {
                reservationList.add(ReservationAdopter.getReservation(reservation));
            }
        }
        return reservationList;
    }
}
