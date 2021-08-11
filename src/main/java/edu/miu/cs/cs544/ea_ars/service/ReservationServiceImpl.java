package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import edu.miu.cs.cs544.ea_ars.domain.Ticket;
import edu.miu.cs.cs544.ea_ars.dto.ReservationAdopter;
import edu.miu.cs.cs544.ea_ars.dto.ReservationDTO;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.dto.adapter.FlightDTOAdapter;
import edu.miu.cs.cs544.ea_ars.repository.FlightRepository;
import edu.miu.cs.cs544.ea_ars.repository.ReservationRepository;
import edu.miu.cs.cs544.ea_ars.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private TicketRepository ticketRepository;

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

	public ReservationDTO addReservation(ReservationDTO reservationDTO, String flightNumber) {
		
		//get flight by flightNumber
		FlightDTO flight = flightService.findFlight(flightNumber);

		if (flight != null) {

			//check capacity
			if (flight.getCapacity() <= 500) {

				flightService.updateCapacity(flight.getFlightNumber(), flight.getCapacity()+1);
				
				Reservation reservation = reservationRepository.save(ReservationAdopter.getReservation(reservationDTO));
				//new ticket
				Ticket ticket = new Ticket(Integer.parseInt(flight.getFlightNumber()), reservationDTO.getReservationCode(),
						flight.getDepartureDate(), flightService.findFlightByNumber(flightNumber), reservation);

				ticketRepository.save(ticket);
//
				return ReservationAdopter.getReservationDTO(reservation);
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
