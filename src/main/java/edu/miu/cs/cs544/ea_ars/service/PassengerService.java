package edu.miu.cs.cs544.ea_ars.service;


import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.PassengerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PassengerService {
    public Page<Passenger> findAll(Pageable pageable);

    public List<PassengerDTO> findAll();

    public PassengerDTO findOne(Long id);

    public PassengerDTO addPassenger(PassengerDTO passengerDTO);

    public void deletePassenger(Long id);

    public PassengerDTO updatePassenger(Long id, PassengerDTO passengerDTO);

    public void flush();
}

