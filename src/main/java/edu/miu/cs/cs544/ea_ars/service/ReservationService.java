package edu.miu.cs.cs544.ea_ars.service;


import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationService {
    public List<ReservationDTO> findAll();

    public Page<Reservation> findAll(Pageable pageable);

    public ReservationDTO findOne(Long id);

    public ReservationDTO addReservation(ReservationDTO reservationDTO);

    public void deleteReservation(Long id);

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);

}
