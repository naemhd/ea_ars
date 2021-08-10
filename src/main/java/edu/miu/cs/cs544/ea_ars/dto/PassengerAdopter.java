package edu.miu.cs.cs544.ea_ars.dto;

import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class PassengerAdopter {

    public static Passenger getPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        if (passengerDTO != null) {
            passenger = new Passenger(passengerDTO.getFirstName(), passengerDTO.getLastName(),passengerDTO.getEmail(),
                    passengerDTO.getDateOfBirth(), passengerDTO.getAddress(),
                    ReservationAdopter.getReservationList(passengerDTO.getReservations()));
        }
        return passenger;
    }

    public static PassengerDTO getPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        if (passenger != null) {
            passengerDTO = new PassengerDTO(passenger.getFirstName(), passenger.getLastName(),passenger.getEmail(),
                    passenger.getDateOfBirth(), passenger.getAddress(),
                    ReservationAdopter.getReservationDTOList(passenger.getReservations()));
        }
        return passengerDTO;
    }

    public static List<PassengerDTO> getPassengerDTOList(List<Passenger> passengerList) {
        List<PassengerDTO> passengerDTOList = new ArrayList<>();
        if (passengerList.size() != 0) {
            for (Passenger passenger : passengerList) {
                passengerDTOList.add(PassengerAdopter.getPassengerDTO(passenger));
            }
        }
        return passengerDTOList;
    }
}
