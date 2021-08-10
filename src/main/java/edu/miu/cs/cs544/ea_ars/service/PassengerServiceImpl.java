package edu.miu.cs.cs544.ea_ars.service;


import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.PassengerAdopter;
import edu.miu.cs.cs544.ea_ars.dto.PassengerDTO;
import edu.miu.cs.cs544.ea_ars.dto.ReservationAdopter;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Page<Passenger> findAll(Pageable pageable) {
        return passengerRepository.findAll(pageable);
    }

    public List<PassengerDTO> findAll() {
        List<Passenger> passengerList = passengerRepository.findAll();
        List<PassengerDTO> passengerDTOs = new ArrayList<>();
        if (passengerList.size() != 0) {
            for (Passenger passenger : passengerList) {
                passengerDTOs.add(PassengerAdopter.getPassengerDTO(passenger));
            }
        }
        return passengerDTOs;

    }

    public PassengerDTO findOne(Long id) {
        if (passengerRepository.findById(id).isPresent()) {
            return PassengerAdopter.getPassengerDTO(passengerRepository.findById(id).get());
        }
        return null;
    }

    public PassengerDTO addPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = PassengerAdopter.getPassenger(passengerDTO);
        return PassengerAdopter.getPassengerDTO(passengerRepository.save(passenger));

    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    public PassengerDTO updatePassenger(Long id, PassengerDTO passengerDTO) {
        if (passengerRepository.findById(id).isPresent()) {

            Passenger passenger1 = passengerRepository.findById(id).get();
            passenger1.setFirstName(passengerDTO.getFirstName());
            passenger1.setLastName(passengerDTO.getLastName());
            passenger1.setAddress(passengerDTO.getAddress());
            passenger1.setEmail(passengerDTO.getEmail());
            passenger1.setDateOfBirth(passengerDTO.getDateOfBirth());

            return PassengerAdopter.getPassengerDTO(passengerRepository.save(passenger1));
        }
        return null;
    }

    public void flush() {
        passengerRepository.flush();
    }

}
