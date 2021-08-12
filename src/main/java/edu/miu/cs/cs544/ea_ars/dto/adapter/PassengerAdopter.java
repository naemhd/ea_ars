package edu.miu.cs.cs544.ea_ars.dto.adapter;

import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.PassengerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerAdopter {

    public static Passenger getPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        if (passengerDTO != null) {
           passenger.setId(passengerDTO.getId());
           passenger.setFirstName(passengerDTO.getFirstName());
           passenger.setLastName(passengerDTO.getLastName());
           passenger.setDateOfBirth(passengerDTO.getDateOfBirth());
           passenger.setEmail(passengerDTO.getEmail());
           passenger.setAddress(passengerDTO.getAddress());
           passenger.setReservations(passengerDTO.getReservations());
        }
        return passenger;
    }

    public static PassengerDTO getPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        if (passenger != null) {
            passengerDTO.setId(passenger.getId());
            passengerDTO.setFirstName(passenger.getFirstName());
            passengerDTO.setLastName(passenger.getLastName());
            passengerDTO.setDateOfBirth(passenger.getDateOfBirth());
            passengerDTO.setEmail(passenger.getEmail());
            passengerDTO.setAddress(passenger.getAddress());
            passengerDTO.setReservations(passenger.getReservations());
        }
        return passengerDTO;
    }

    public static List<PassengerDTO> getPassengerDTOList(List<Passenger> passengerList) {
        return passengerList.stream().map(r->getPassengerDTO(r)).collect(Collectors.toList());
    }
    public static List<Passenger> getPassengerList(List<PassengerDTO> passengerDTOList) {
        return passengerDTOList.stream().map(r->getPassenger(r)).collect(Collectors.toList());
    }
}
