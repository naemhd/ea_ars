package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.dto.PassengerAdopter;
import edu.miu.cs.cs544.ea_ars.dto.ReservationAdopter;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.dto.adapter.FlightDTOAdapter;
import edu.miu.cs.cs544.ea_ars.repository.FlightRepository;
import edu.miu.cs.cs544.ea_ars.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FlightRepository flightRepository;

    public List<ReservationDTO> findAll() {
       List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        if(reservationList.size()!=0) {
            for (Reservation reservation : reservationList) {
                reservationDTOS.add(ReservationAdopter.getReservationDTO(reservation));
            }
        }
        return reservationDTOS;
    }

    public Page<Reservation> findAll(Pageable pageable){
       return reservationRepository.findAll(pageable);
    }
    public ReservationDTO findOne(Long id) {
        //  Reservation reservation = reservationRepository.findById(id).get();
        if (reservationRepository.findById(id).isPresent()) {

            return ReservationAdopter.getReservationDTO(reservationRepository.findById(id).get());
        }
        return null;
    }

    public ReservationDTO addReservation(ReservationDTO reservationDTO,Long flightId) {
    	
    	Optional<Flight> flight = flightRepository.findById(flightId);
    	
    	if(flight.isPresent()) {
    		Flight f = flight.get();
    		
    		if(f.getCapacity() <= 500) {

        		f.setCapacity(f.getCapacity() + 1);
        		
        		
        		flightRepository.save(f);
        		
        		Reservation reservation = ReservationAdopter.getReservation(reservationDTO);
                
        		
        		return ReservationAdopter.getReservationDTO(reservationRepository.save(reservation));
        	}
    	}
    	
    	
    	
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        if (reservationRepository.findById(id).isPresent()) {
            Reservation reservationToBeUpdated = reservationRepository.findById(id).get();
            reservationToBeUpdated.setReservationCode(reservationDTO.getReservationCode());
            reservationToBeUpdated.setReservationDate(reservationDTO.getReservationDate());
            reservationToBeUpdated.setReservedBy(reservationDTO.getReservedBy());
            reservationToBeUpdated.setPaid(reservationDTO.isPaid());
            reservationToBeUpdated.setReservedBy(reservationDTO.getReservedBy());

            return ReservationAdopter.getReservationDTO(reservationRepository.save(reservationToBeUpdated));
        }
        return null;
    }
}
