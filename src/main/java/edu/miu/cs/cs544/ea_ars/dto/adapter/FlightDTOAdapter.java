package edu.miu.cs.cs544.ea_ars.dto.adapter;

import edu.miu.cs.cs544.ea_ars.domain.Flight;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import edu.miu.cs.cs544.ea_ars.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FlightDTOAdapter {

    @Autowired
    private FlightService flightService;

    public static FlightDTO flightDTOAdapter(Flight flight){

        FlightDTO flightdto = new FlightDTO();
        flightdto.setFlightNumber(flight.getFlightNumber());
        flightdto.setCapacity(flight.getCapacity());
        flightdto.setDepartureDate(flight.getDepartureDate());
        flightdto.setDepartureTime(flight.getDepartureTime());
        flightdto.setArrivalDate(flight.getArrivalDate());
        flightdto.setArrivalTime(flight.getArrivalTime());
        flightdto.setAirline(flight.getAirline());
        flightdto.setOrigin(flight.getOrigin());
        flightdto.setDestination(flight.getDestination());

        return flightdto;
    }

    public static List<FlightDTO> flightsDTOAdapter(List<Flight> flights){
        return (flights.stream().map(flight->flightDTOAdapter(flight)).collect(Collectors.toList()));
    }

    public static  Flight dtoToFlight(FlightDTO flightDTO){

        Flight flight=new Flight();

        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setCapacity(flightDTO.getCapacity());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setArrivalTime(flightDTO.getArrivalTime());

        flight.setAirline(flightDTO.getAirline());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());

        return flight;
    }
}